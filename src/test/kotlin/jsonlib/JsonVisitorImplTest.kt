package jsonlib

import jsonlib.jsonTypeObjects.*
import jsonlib.visitor.JsonVisitorImpl
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class JsonVisitorImplTest {

    @Test
    fun objTestDuplicateKeys() {
        val jsonObject = JsonObject(
            mapOf(
                "name" to JsonString("Fred"),
                "age" to JsonNumber(27),
                "map" to JsonObject(
                    mapOf(
                        "key" to JsonNumber(1),
                        " key " to JsonNumber(2)
                    )
                )
            )
        )

        val visitor = JsonVisitorImpl()

        jsonObject.accept(visitor)

        assertFalse(visitor.isValid)
        assertTrue(visitor.getErrors().any() {it.equals("Chave duplicada 'key'")})
    }

    @Test
    fun objTestEmptyKeys() {
        val jsonObject = JsonObject(
            mapOf(
                "name" to JsonString("Fred"),
                " " to JsonString("Empty"),
                "other" to JsonString("Test")
            )
        )

        val visitor = JsonVisitorImpl()

        jsonObject.accept(visitor)

        assertFalse(visitor.isValid)
        assertTrue(visitor.getErrors().any() {it.equals("Chave inválida: ' '")})
    }

    @Test
    fun objVisitValid() {
        val jsonObject = JsonObject(
            mapOf(
                "name" to JsonString("Fred"),
                "age" to JsonNumber(27)
            )
        )

        val visitor = JsonVisitorImpl()

        jsonObject.accept(visitor)

        assertTrue(visitor.isValid)
        assertTrue(visitor.getErrors().isEmpty())
    }

    @Test
    fun arrayDetectNull() {
        val arr = JsonArray(listOf(
            JsonString("hello"),
            JsonNull
        ))

        val visitor = JsonVisitorImpl()
        arr.accept(visitor)

        assertFalse(visitor.isValid)
        assertTrue(visitor.getErrors().any { it.equals("O array contém um elemento a null") })
    }

    @Test
    fun arrayMixedTypes() {
        val arr = JsonArray(listOf(
            JsonString("hello"),
            JsonString("bye"),
            JsonBoolean(true),
            JsonNumber(22)
        ))

        val visitor = JsonVisitorImpl()
        arr.accept(visitor)

        assertFalse(visitor.isValid)
        assertTrue(visitor.getErrors().any { it.equals("O array contém diferentes tipos JSON: 'JsonString' e " +
                "'JsonBoolean'") })
    }

    @Test
    fun arrayVisitValid() {
        val arr = JsonArray(listOf(
            JsonString("hello"),
            JsonString("olá"),
            JsonString("hola"),
            JsonString("Konnichiwa")
        ))

        val visitor = JsonVisitorImpl()
        arr.accept(visitor)

        assertTrue(visitor.isValid)
        assertTrue(visitor.getErrors().isEmpty())
    }
}