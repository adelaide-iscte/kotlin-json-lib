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
     * Aceita um visitante que implementa o padrão Visitor.
     *
     * Permite aplicar uma operação externa ao valor nulo.
     *
     * @param visitor o visitante a aplicar.
     */
    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
