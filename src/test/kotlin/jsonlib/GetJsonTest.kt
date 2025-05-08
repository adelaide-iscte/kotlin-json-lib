package jsonlib

import jsonlib.getjson.GetJson
import org.junit.jupiter.api.*
import testapi.TestController
import java.net.Socket
import kotlin.concurrent.thread

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetJsonTest {

    private val port = 8081
    private lateinit var serverThread: Thread

    @BeforeAll
    fun startServer() {
        val app = GetJson(TestController::class)
        serverThread = thread { app.start(port) }
        Thread.sleep(300)
    }

    @Test
    fun testHelloEndpoint() {
        val response = makeRequest("/api/hello")
        assert(response.contains("Olá, mundo!"))
    }

    @Test
    fun testEchoEndpoint() {
        val response = makeRequest("/api/echo?msg=oi")
        assert(response.contains("Eco: oi"))
    }

    @Test
    fun testSomaEndpoint() {
        val response = makeRequest("/api/soma?a=2&b=3")
        assert(response.contains("5"))
    }

    private fun makeRequest(path: String): String {
        Socket("localhost", port).use { socket ->
            val writer = socket.getOutputStream().bufferedWriter()
            val reader = socket.getInputStream().bufferedReader()

            writer.write("GET $path HTTP/1.1\r\nHost: localhost\r\n\r\n")
            writer.flush()

            return reader.readText()
        }
    }
}
