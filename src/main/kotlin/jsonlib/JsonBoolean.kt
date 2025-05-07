package jsonlib

/**
 * Representa um valor JSON booleano.
 *
 * @param value Valor booleano.
 */
data class JsonBoolean(val value: Boolean) : JsonValue() {

    /**
     * Converte o booleano para string no formato JSON.
     */
    override fun toJsonString(): String = value.toString()
}
