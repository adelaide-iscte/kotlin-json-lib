package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor

/**
 * Representa um array JSON.
 *
 * @param elements Lista de elementos JSON.
 */
data class JsonArray(val elements: List<JsonValue>) : JsonValue {

    /**
     * Converte o array para uma string JSON.
     */
    override fun toJsonString(): String =
        elements.joinToString(prefix = "[", postfix = "]", separator = ",") { it.toJsonString() }

    /**
     * Aceita um visitante que implementa o padrão Visitor.
     *
     * Este método permite aplicar operações externas ao conteúdo do array
     * sem modificar a sua estrutura, de forma recursiva.
     */
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
