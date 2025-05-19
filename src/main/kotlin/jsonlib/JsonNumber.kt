package jsonlib

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
}
