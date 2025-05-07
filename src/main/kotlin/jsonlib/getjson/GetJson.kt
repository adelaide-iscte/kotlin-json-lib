package jsonlib.getjson

import jsonlib.JsonValue
import java.net.*
import java.util.concurrent.Executors
import kotlin.reflect.KClass
import kotlin.reflect.full.functions

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
