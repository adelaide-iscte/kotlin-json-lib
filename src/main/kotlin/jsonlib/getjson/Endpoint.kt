package jsonlib.getjson

import jsonlib.JsonValue
import jsonlib.inferJsonValue
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.valueParameters

//Endpoint - Ade não sabe programar

class Endpoint(
    val path: String,
    val instance: Any,
    val function: KFunction<*>
) {
    fun dispatch(query: Map<String, String>): JsonValue {
        val args = function.parameters.map { param ->
            if (param.kind == KParameter.Kind.INSTANCE) {
                instance
            } else {
                val name = param.findAnnotation<Param>()?.name ?: param.name
                val raw = query[name] ?: error("Faltando parâmetro: $name")
                raw
            }
        }

        val result = function.call(*args.toTypedArray())
        return inferJsonValue(result)
    }
}
