package jsonlib

import jsonlib.jsonTypeObjects.JsonString
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonStringTest {

    @Test
    fun testToJsonString() {
        val js = JsonString("hello")
        assertEquals("\"hello\"", js.toJsonString())
    }

    // Aspas dentro da string, para serem reconhecidas como aspas devemos por \"
    @Test
    fun testEscapeQuotes() {
        val js = JsonString("hello \"big\" world")
        assertEquals("\"hello \\\"big\\\" world\"", js.toJsonString())
    }

    // Barras dentro da string, para serem reconhecidas como barras temos que por \\
    @Test
    fun testEscapeBackslash() {
        val js = JsonString("folder\\file")
        assertEquals("\"folder\\\\file\"", js.toJsonString())
    }
}
