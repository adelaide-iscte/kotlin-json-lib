import jsonlib.getjson.GetJson
import testapi.TestController
/**
 *
 * Regista o controlador e arranca o servidor HTTP na porta 8081.
 */
fun main() {
    val api = GetJson(TestController::class)
    println("Para encerrar o processo: CTRL + C | CMD + C (MAC)")
    println(">> Servidor inicializado no URL localhost:8081")
    println(">> Exemplo: localhost:8081/api/hello")
    api.start(8081)
}