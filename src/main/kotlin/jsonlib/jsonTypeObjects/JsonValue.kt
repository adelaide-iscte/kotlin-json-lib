package jsonlib.jsonTypeObjects

/**
 * Interface base para todos os tipos de valor JSON.
 */
sealed interface JsonValue {
    fun toJsonString(): String
}