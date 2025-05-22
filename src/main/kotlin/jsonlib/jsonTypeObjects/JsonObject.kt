package jsonlib.jsonTypeObjects

import jsonlib.visitor.JsonVisitor

/**
 * Representa um objeto JSON.
 *
 * @param properties Mapa de chaves para valores JSON.
 */

data class JsonObject(val properties: Map<String, JsonValue>) : JsonValue {

    /**
     * Converte o número para string no formato JSON.
     */

    override fun toJsonString(): String =
        toJsonString("   ",0)

    /**
     * Aceita um visitante que implementa o padrão Visitor.
     *
     * Permite aplicar uma operação externa ao objeto JSON.
     *
     * @param visitor o visitante a aplicar.
     */

    override fun accept(visitor: JsonVisitor) {
        return visitor.visit(this)
    }

    /**
     * Converte o objeto para o formato de "pretty" string JSON.
     * @param indent: Representa a identação ("tab")
     * @param level: Representa o nível hierárquico
     */

    private fun toJsonString(indent: String, level: Int): String =
        properties.entries.joinToString(
            prefix = "{\n"
            , postfix = "\n${indent.repeat(level)}}"
            , separator = ",\n"
        ) { (key, value) ->
            "${indent.repeat(level + 1)}\"$key\": ${value.toJsonString(indent, level + 1)}"
        }

    /**
     * Função auxiliar privada que torna possível a recursividade na identação dos diferentes
     * níveis de um json
     */

    private fun JsonValue.toJsonString(indent: String, level: Int): String =
        when (this) {
            is JsonObject -> this.toJsonString(indent, level)
            else -> this.toJsonString()
        }


    /**
     * Retorna um novo JsonObject com os que passam no predicado.
     */

    fun filter(predicate: (String, JsonValue) -> Boolean): JsonObject =
        JsonObject(properties.filter { (key, value) -> predicate(key, value) })

}
