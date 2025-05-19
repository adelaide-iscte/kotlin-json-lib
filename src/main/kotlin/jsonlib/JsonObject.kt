package jsonlib

/**
 * Representa um objeto JSON.
 *
 * @param properties Mapa de chaves para valores JSON.
 */
data class JsonObject(val properties: Map<String, JsonValue>) : JsonValue {

    override fun toJsonString(): String =
        toJsonString("   ",0)

    /**
     * Converte o objeto para string JSON.
     */
//    override fun toJsonString(): String =
//        properties.entries.joinToString(prefix = "{\n", postfix = "\n}", separator = ",\n") {
//            "\"${it.key}\":${it.value.toJsonString()}"
//        }

    private fun toJsonString(indent: String, level: Int): String =
        properties.entries.joinToString(
            prefix = "{\n"
            , postfix = "\n${indent.repeat(level)}}"
            , separator = ",\n"
        ) { (key, value) ->
            "${indent.repeat(level + 1)}\"$key\": ${value.toJsonString(indent, level + 1)}"
        }

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
