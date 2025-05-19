package jsonlib

import kotlin.test.Test
import kotlin.test.assertEquals

class JsonArrayTest {

    @Test
    fun testToJsonString() {
        val array = JsonArray(listOf(JsonNumber(1), JsonString("hello"), JsonBoolean(true)))
        val expected = "[1,\"hello\",true]"
        assertEquals(expected, array.toJsonString())
    }

    @Test
    fun testMapDoubledNumbers() {
        val array = JsonArray(listOf(JsonNumber(1), JsonNumber(2), JsonNumber(3)))
        val mapped = array.map {
            if (it is JsonNumber)
                JsonNumber(it.value.toInt() * 2)
            else it
        }
        val expected = "[2,4,6]"
        assertEquals(expected, mapped.toJsonString())
    }

    @Test
    fun testFilterEvenNumbers() {
        val array = JsonArray(listOf(JsonNumber(1), JsonNumber(2), JsonNumber(3), JsonNumber(4)))

        val filtered = array.filter {
            it is JsonNumber && it.value.toInt() % 2 == 0
        }

        val expected = "[2,4]"
        assertEquals(expected, filtered.toJsonString())
    }
}
