package jsonlib.getjson

import jsonlib.JsonValue
import jsonlib.inferJsonValue
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation

/**
 * Classe que representa um endpoint HTTP (como uma função que pode ser chamada por URL).
 *
 * Cada endpoint liga uma função Kotlin a um caminho específico (ex: "/api/soma").
 * Quando alguém acede a esse caminho no browser, esta classe trata de invocar a função.
 *
 * @param path O caminho (rota) associado, por exemplo "/api/echo"
 * @param instance A instância da classe onde está a função
 * @param function A função Kotlin que vai ser chamada
 */
class Endpoint(
    val path: String,
    val instance: Any,
    val function: KFunction<*>
) {
    /**
     * Executa a função com base nos parâmetros que vêm da query da URL.
     *
     * Por exemplo, se a função tiver @Param("msg"), vai buscar o valor de "msg"
     * na query string e passa esse valor para a função.
     *
     * O valor de retorno é convertido automaticamente para JSON com inferJsonValue().
     *
     * @param query Mapa com os parâmetros da URL (ex: ?msg=oi → ["msg" to "oi"])
     * @return O resultado convertido num JsonValue (string, número, etc)
     */
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
