package jsonlib

/**
 * Representa um valor JSON do tipo string.
 *
 * @param value O conteúdo da string.
 */
data class JsonString(val value: String) : JsonValue() {

    /**
     * Converte a string para uma um JSON válido.
     */
    override fun toJsonString(): String {
        return "\"" + value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"") +
                "\""
    }
}