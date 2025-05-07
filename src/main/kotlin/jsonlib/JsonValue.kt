package jsonlib

/**
 * Classe base para todos os tipos de valor JSON.
 */
sealed class JsonValue {
    abstract fun toJsonString(): String
}