package jsonlib

import kotlin.test.Test
import kotlin.test.assertEquals

class JsonInferTest {

    @Test
    fun testInferString() {
        val json = inferJsonValue("hello")
        assertEquals("\"hello\"", json.toJsonString())
    }

    @Test
    fun testInferNumber() {
        val json = inferJsonValue(55)
        assertEquals("55", json.toJsonString())
    }

    @Test
    fun testInferBoolean() {
        val json = inferJsonValue(true)
        assertEquals("true", json.toJsonString())
    }

    @Test
    fun testInferNull() {
        val json = inferJsonValue(null)
        assertEquals("null", json.toJsonString())
    }

    @Test
    fun testInferList() {
        val json = inferJsonValue(listOf(1, "a", false))
        assertEquals("[1,\"a\",false]", json.toJsonString())
    }

    @Test
    fun testInferMap() {
        val json = inferJsonValue(mapOf("bronze" to 1, "iron" to "xyz"))
        val expected =
            "{\n" +
                "   \"bronze\": 1,\n" +
                "   \"iron\": \"xyz\"" +
            "\n}"
        assertEquals(expected, json.toJsonString())
    }

    @Test
    fun testInferDataClass() {
        data class User(val name: String, val active: Boolean)
        val json = inferJsonValue(User("Pedro", true))
        val expected =
            "{\n" +
                "   \"name\": \"Pedro\",\n" +
                "   \"active\": true" +
            "\n}"
        assertEquals(expected, json.toJsonString())
    }

    @Test
    fun testInferNestedDataClass() {
        data class Address(val city: String)
        data class Person(val name: String, val address: Address)

        val json = inferJsonValue(Person("Adelaide", Address("Setúbal")))

        val expected =
            "{\n" +
            "   \"name\": \"Adelaide\",\n" +
            "   \"address\": {\n" +
            "      \"city\": \"Setúbal\"\n" +
            "   }" +
            "\n}"

        println(json.toJsonString())

        assertEquals(expected, json.toJsonString())
    }
}
