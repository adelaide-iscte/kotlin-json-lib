package jsonlib.jsonTypeObjects

import jsonlib.visitor.Visitable

/**
 * Interface base para todos os tipos de valor JSON.
 */
sealed interface JsonValue : Visitable {
    fun toJsonString(): String
}