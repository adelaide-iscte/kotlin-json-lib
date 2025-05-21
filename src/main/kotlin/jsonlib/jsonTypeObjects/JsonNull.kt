package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor

/**
 * Representa o tipo JSON nulo.
 */

// Tem sempre o mesmo valor, então vamos criar como um object

data object JsonNull : JsonValue {

    /**
     * Converte para a string "null".
     */
    override fun toJsonString(): String = "null"

    /**
     * TODO
     */
    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
