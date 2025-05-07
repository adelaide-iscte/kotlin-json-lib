import jsonlib.getjson.GetJson
import testapi.TestController

fun main() {
    val api = GetJson(TestController::class)
    api.start(8081)
}