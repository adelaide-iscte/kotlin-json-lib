package jsonlib

/**
 * Representa o valor JSON nulo.
 */

// Tem sempre o mesmo valor, então vamos criar como um object

data object JsonNull : JsonValue() {

    /**
     * Converte para a string "null".
     */
    override fun toJsonString(): String = "null"
}
