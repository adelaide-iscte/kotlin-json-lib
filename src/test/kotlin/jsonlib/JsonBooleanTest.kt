package jsonlib

import jsonlib.jsonTypeObjects.JsonBoolean
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonBooleanTest {

    @Test
    fun testTrue() {
        val b = JsonBoolean(true)
        assertEquals("true", b.toJsonString())
    }

    @Test
    fun testFalse() {
        val b = JsonBoolean(false)
        assertEquals("false", b.toJsonString())
    }
}
