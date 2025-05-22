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
     * Aceita um visitante que implementa o padrão Visitor.
     *
     * Permite aplicar uma operação externa ao valor numérico
     * sem alterar a sua estrutura.
     *
     * @param visitor o visitante a aplicar.
     */
    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}
