package testapi

import jsonlib.getjson.*

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
