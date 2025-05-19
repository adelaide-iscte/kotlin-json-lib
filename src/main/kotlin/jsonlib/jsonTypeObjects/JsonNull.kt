package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor
import jsonlib.visitor.Visitable

/**
 * Representa o valor JSON nulo.
 */

// Tem sempre o mesmo valor, então vamos criar como um object

data object JsonNull : JsonValue, Visitable {

    /**
     * Converte para a string "null".
     */
    override fun toJsonString(): String = "null"

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
