package jsonlib

import jsonlib.jsonTypeObjects.JsonNumber
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonNumberTest {

    @Test
    fun testIntNumber() {
        val n = JsonNumber(13)
        assertEquals("13", n.toJsonString())
    }

    @Test
    fun testNegativeNumber() {
        val n = JsonNumber(-13)
        assertEquals("-13", n.toJsonString())
    }

    @Test
    fun testDoubleNumber() {
        val n = JsonNumber(1.23)
        assertEquals("1.23", n.toJsonString())
    }

}
