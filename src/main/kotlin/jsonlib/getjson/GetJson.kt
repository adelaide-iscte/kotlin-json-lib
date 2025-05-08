package jsonlib.getjson

import jsonlib.JsonValue
import java.net.*
import java.util.concurrent.Executors
import kotlin.reflect.KClass
import kotlin.reflect.full.functions

/**
 * Pequeno servidor HTTP que permite criar endpoints a partir de funções Kotlin.
 *
 * A ideia é passar uma ou mais classes com funções anotadas com @Mapping,
 * e o servidor trata de ligar essas funções a rotas HTTP.
 *
 * Quando alguém acede à rota no browser, a função é chamada automaticamente
 * e a resposta é enviada em formato JSON.
 *
 * @param controllers Lista de classes com funções que queremos expor como endpoints
 */
class GetJson(vararg controllers: KClass<*>) {
    private val endpoints = mutableListOf<Endpoint>()

    init {
        controllers.forEach { controllerClass ->
            val instance = controllerClass.constructors.first().call()
            val classPath = controllerClass.annotations
                .filterIsInstance<Mapping>()
                .firstOrNull()?.path ?: ""

            controllerClass.functions
                .filter { it.annotations.any { it is Mapping } }
                .forEach { function ->
                    val methodPath = function.annotations
                        .filterIsInstance<Mapping>()
                        .first().path
                    val fullPath = "$classPath/$methodPath".replace("//", "/")
                    endpoints.add(Endpoint(fullPath, instance, function))
                }
        }
    }

    /**
     * Arranca o servidor na porta indicada. Fica à espera de pedidos GET.
     *
     * Cada pedido é tratado por uma thread e, se corresponder a uma rota conhecida,
     * devolve o resultado da função em JSON.
     *
     * @param port Número da porta (ex: 8081)
     */
    fun start(port: Int) {
        val server = ServerSocket(port)
        val pool = Executors.newCachedThreadPool()

        while (true) {
            val client = server.accept()
            pool.submit {
                handleClient(client)
            }
        }
    }

    /**
     * Trata de um pedido HTTP vindo do cliente.
     *
     * Verifica se é GET, extrai a rota e os parâmetros, chama o endpoint correspondente
     * e devolve a resposta em JSON. Se não encontrar a rota, responde 404.
     *
     * @param socket A ligação com o cliente (browser ou curl)
     */
    private fun handleClient(socket: Socket) {
        socket.use {
            val reader = it.getInputStream().bufferedReader()
            val writer = it.getOutputStream().bufferedWriter()

            val requestLine = reader.readLine() ?: return
            val (method, pathWithQuery) = requestLine.split(" ")

            if (method != "GET") {
                writer.write("HTTP/1.1 405 Method Not Allowed\r\n\r\n")
                writer.flush()
                return
            }

            val (path, query) = pathWithQuery.split("?", limit = 2).let {
                it[0] to it.getOrElse(1) { "" }
            }

            val endpoint = endpoints.find { it.path == path }

            if (endpoint == null) {
                writer.write("HTTP/1.1 404 Not Found\r\n\r\n")
            } else {
                val queryMap = query.split("&").mapNotNull {
                    val parts = it.split("=")
                    if (parts.size == 2) parts[0] to parts[1] else null
                }.toMap()

                val json = endpoint.dispatch(queryMap)
                writer.write("HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n")
                writer.write(json.toJsonString())
            }

            writer.flush()
        }
    }
}
