package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor

/**
 * Representa um valor JSON booleano.
 *
 * @param value Valor booleano.
 */
data class JsonBoolean(val value: Boolean) : JsonValue {

    /**
     * Converte o booleano para string no formato JSON.
     */

    override fun toJsonString(): String = value.toString()

    /**
     * Aceita um visitante que implementa o padrão Visitor.
     *
     * Permite aplicar uma operação externa ao valor booleano.
     *
     * @param visitor o visitante a aplicar.
     */

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
