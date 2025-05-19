package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor
import jsonlib.visitor.Visitable

/**
 * Representa um array JSON.
 *
 * @param elements Lista de elementos JSON.
 */
data class JsonArray(val elements: List<JsonValue>) : JsonValue, Visitable {

    /**
     * Converte o array para uma string JSON.
     */
    override fun toJsonString(): String =
        elements.joinToString(prefix = "[", postfix = "]", separator = ",") { it.toJsonString() }

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }

    /**
     * Retorna um novo JsonArray com os elementos transformados.
     */
    fun map(transform: (JsonValue) -> JsonValue): JsonArray =
        JsonArray(elements.map(transform))

    /**
     * Retorna um novo JsonArray com os elementos que passam no filtro.
     */
    fun filter(predicate: (JsonValue) -> Boolean): JsonArray =
        JsonArray(elements.filter(predicate))


}
