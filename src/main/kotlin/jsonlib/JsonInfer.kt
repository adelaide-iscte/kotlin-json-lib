package jsonlib

import jsonlib.jsonTypeObjects.*
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * Infere automaticamente uma instância de [JsonValue] a partir de qualquer objeto Kotlin.
 *
 * Esta função suporta:
 * - Strings → [JsonString]
 * - Números (Int, Double, etc.) → [JsonNumber]
 * - Booleanos → [JsonBoolean]
 * - null → [JsonNull]
 * - Listas → [JsonArray]
 * - Mapas com chaves String → [JsonObject]
 * - Data classes → [JsonObject]
 *
 * Lança [IllegalArgumentException] se o tipo não for suportado.
 *
 * @param any qualquer valor Kotlin
 * @return uma representação do valor como [JsonValue]
 */

fun inferJsonValue(any: Any?): JsonValue {
    return when (any) {
        null -> JsonNull
        is String -> JsonString(any)
        is Number -> JsonNumber(any)
        is Boolean -> JsonBoolean(any)
        is List<*> -> JsonArray(any.map { inferJsonValue(it) })
        is Map<*, *> -> {
            val pairs = mutableMapOf<String, JsonValue>()
            for ((k, v) in any) {
                if (k is String) {
                    pairs[k] = inferJsonValue(v)
                }
            }
            JsonObject(pairs)
        }
        else -> {
            val cls = any::class
            if (cls.isData) {
                val props = mutableMapOf<String, JsonValue>()
                val params = cls.primaryConstructor!!.parameters
                for (p in params) {
                    // Encontra a propriedade com o mesmo nome, obtém o valor da propriedade e converte recursivamente
                    val prop = cls.memberProperties.first { it.name == p.name }
                    val value = prop.call(any)
                    props[p.name!!] = inferJsonValue(value)
                }
                JsonObject(props)
            } else {
                throw IllegalArgumentException("Tipo não suportado")
            }
        }
    }
}
