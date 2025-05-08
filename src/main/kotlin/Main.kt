import jsonlib.getjson.GetJson
import testapi.TestController
/**
 *
 * Regista o controlador e arranca o servidor HTTP na porta 8081.
 */
fun main() {
    val api = GetJson(TestController::class)
    api.start(8081)
}