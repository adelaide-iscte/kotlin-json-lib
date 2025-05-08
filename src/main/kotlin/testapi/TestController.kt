package testapi

import jsonlib.getjson.*

/**
 * Controlador de teste usado para validar o funcionamento da mini-framework GetJson.
 * Expõe três endpoints:
 * - /api/hello → retorna uma saudação
 * - /api/echo → repete a mensagem recebida
 * - /api/soma → soma dois números recebidos por query
 */
@Mapping("/api")
class TestController {

    @Mapping("/hello")
    fun hello(): String = "Olá, mundo!"

    @Mapping("/echo")
    fun echo(@Param("msg") mensagem: String): String = "Eco: $mensagem"

    @Mapping("/soma")
    fun soma(@Param("a") a: String, @Param("b") b: String): Int {
        return a.toInt() + b.toInt()
    }
}
