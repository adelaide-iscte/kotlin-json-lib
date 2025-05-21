package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor

/**
 * Representa um valor JSON do tipo numérico.
 *
 * @param value O número a armazenar.
 */
data class JsonNumber(val value: Number) : JsonValue {

    /**
     * Converte o número para string no formato JSON.
     */
    override fun toJsonString(): String = value.toString()

    /**
     * TODO
     */
    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
