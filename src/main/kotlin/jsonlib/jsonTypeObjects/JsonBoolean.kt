package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor
import jsonlib.visitor.Visitable

/**
 * Representa um valor JSON booleano.
 *
 * @param value Valor booleano.
 */
data class JsonBoolean(val value: Boolean) : JsonValue, Visitable {

    /**
     * Converte o booleano para string no formato JSON.
     */
    override fun toJsonString(): String = value.toString()

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
