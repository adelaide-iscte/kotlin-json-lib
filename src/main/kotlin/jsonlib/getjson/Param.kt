package jsonlib.getjson

/**
 * Anotação usada para indicar o nome de um parâmetro que vem da query string de um pedido GET.
 *
 * É aplicada a parâmetros de funções que queremos expor como endpoints HTTP.
 *
 * @param name Nome do parâmetro esperado na query da URL.
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Param(val name: String)
