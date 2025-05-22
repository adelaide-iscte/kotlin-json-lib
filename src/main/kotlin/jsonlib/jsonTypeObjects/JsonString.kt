package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor

/**
 * Representa um valor JSON do tipo string.
 *
 * @param value O conteúdo da string.
 */
data class JsonString(val value: String) : JsonValue {

    /**
     * Converte a string para uma um JSON válido.
     */
    override fun toJsonString(): String {
        return "\"" + value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"") +
                "\""
    }

    /**
     * Aceita um visitante que implementa o padrão Visitor.
     *
     * Permite aplicar uma operação externa à string JSON.
     *
     * @param visitor o visitante a aplicar.
     */

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}