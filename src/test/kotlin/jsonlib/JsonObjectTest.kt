package jsonlib

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

        val expected = """{"name":"John Meow","age":55,"active":true}"""
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

        val expected = """{"bronze":1,"silver":2}"""
        assertEquals(expected, filtered.toJsonString())
    }
}

