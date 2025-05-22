package jsonlib

import jsonlib.jsonTypeObjects.JsonBoolean
import jsonlib.jsonTypeObjects.JsonNumber
import jsonlib.jsonTypeObjects.JsonObject
import jsonlib.jsonTypeObjects.JsonString
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonObjectTest {

    @Test
    fun testToJsonString() {
        val obj = JsonObject(
            mapOf(
                "name" to JsonString("John Meow"),
                "age" to JsonNumber(55),
                "active" to JsonBoolean(true)
            )
        )

        val expected =
            "{\n" +
                "   \"name\": \"John Meow\",\n" +
                "   \"age\": 55,\n" +
                "   \"active\": true" +
            "\n}"
        assertEquals(expected, obj.toJsonString())
    }

    @Test
    fun testFilter() {
        val obj = JsonObject(
            mapOf(
                "bronze" to JsonNumber(1),
                "silver" to JsonNumber(2),
                "gold" to JsonString("ignore")
            )
        )

        val filtered = obj.filter { key, _ -> key != "gold" }

        val expected =
            "{\n" +
            "   \"bronze\": 1,\n" +
            "   \"silver\": 2" +
            "\n}"
        assertEquals(expected, filtered.toJsonString())
    }
}

