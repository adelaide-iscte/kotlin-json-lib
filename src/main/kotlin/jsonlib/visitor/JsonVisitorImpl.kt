package jsonlib.visitor

import jsonlib.jsonTypeObjects.*
import kotlin.reflect.KClass

class JsonVisitorImpl: JsonVisitor {

    //check if valid
    //check if all JSON Arrays contain values of the same type

    var isValid = true
    private val errors = mutableListOf<String>()

    fun getErrors():List<String> = errors

    /**
     *
     * Função visitor que valida se um JsonArray é válido
     *
     * Esta função valida as seguintes condições:
     *** se existe algum valor JsonNull
     *** se todos os valores são do mesmo tipo
     *
     * Caso se verifique alguma das condições acima descritas, será inserido um erro na
     * lista de erros e a função irá cessar imediatamente
     *
     */

    override fun visit(jsonArray: JsonArray) {

        var firstValType: KClass<out JsonValue>? = null //Tipo que será usado como comparação
                                                        //"out" significa que pode ser qualquer classe de JsonValue

        for(value in jsonArray.elements){

            if(value is JsonNull) {
                isValid = false
                errors.add("O array contém um elemento a null")
                break                                   //Erro detetado, saímos imediatamente
            }

            val type = value::class

            if(firstValType == null)
                firstValType = type

            else if (firstValType != type) {
                isValid = false
                errors.add("O array contém diferentes tipos JSON: '${firstValType.simpleName}' e '${type.simpleName}'")
                break                                   //Erro detetado, saímos imediatamente
            }

            value.accept(this)

            if(!isValid) break                          //cessar da validação em objetos "nested"

        }
    }

    /**
     *
     * Função visitor que valida se um JsonObject é válido
     *
     * Esta função valida as seguintes condições:
     *** se a chave é válida, ou seja, se tem algum valor não vazio
     *** se a chave é repetida
     *
     * Caso se verifique alguma das condições acima descritas, será inserido um erro na
     * lista de erros e a função irá cessar de imediato
     *
     */
    override fun visit(jsonObject: JsonObject) {
        val keys = mutableSetOf<String>()

        for((key, value) in jsonObject.properties){
            val tKey = key.trim()                       //eliminar espaços para validação

            if(tKey.isEmpty()) {
                isValid = false
                errors.add("Chave inválida: '$key'")
                break
            }

            if(!keys.add(tKey)) {
                isValid = false
                errors.add("Chave duplicada '$tKey'")
                break
            }

            value.accept(this)

            if(!isValid) break                          //cessar da validação em objetos "nested"

        }
    }

    override fun visit(jsonBoolean: JsonBoolean) {}

    override fun visit(jsonNull: JsonNull) {}

    override fun visit(jsonNumber: JsonNumber) {}

    override fun visit(jsonString: JsonString) {}
}