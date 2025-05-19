package jsonlib

/**
 * Representa um objeto JSON.
 *
 * @param properties Mapa de chaves para valores JSON.
 */
data class JsonObject(val properties: Map<String, JsonValue>) : JsonValue {

    /**
     * Converte o objeto para string JSON.
     */
    override fun toJsonString(): String =
        properties.entries.joinToString(prefix = "{", postfix = "}", separator = ",") {
            "\"${it.key}\":${it.value.toJsonString()}"
        }

    /**
     * Retorna um novo JsonObject com os que passam no predicado.
     */
    fun filter(predicate: (String, JsonValue) -> Boolean): JsonObject =
        JsonObject(properties.filter { (key, value) -> predicate(key, value) })
}
