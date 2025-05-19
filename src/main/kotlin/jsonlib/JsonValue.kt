package jsonlib

/**
 * Classe base para todos os tipos de valor JSON.
 */
sealed interface JsonValue {
    fun toJsonString(): String
}