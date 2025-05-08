package jsonlib.getjson

/**
 * Anotação usada para definir o caminho (rota) de um endpoint HTTP.
 *
 * Pode ser colocada numa classe (para indicar um prefixo) ou numa função (para indicar a rota final).
 *
 * @param path Caminho a ser associado ao endpoint (ex: "hello", "soma", etc)
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Mapping(val path: String)
