package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor
import jsonlib.visitor.Visitable

/**
 * Representa um valor JSON do tipo string.
 *
 * @param value O conteúdo da string.
 */
data class JsonString(val value: String) : JsonValue, Visitable {

    /**
     * Converte a string para uma um JSON válido.
     */
    override fun toJsonString(): String {
        return "\"" + value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"") +
                "\""
    }

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }
}