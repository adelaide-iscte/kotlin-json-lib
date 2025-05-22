# json-lib

Este é um projeto em Kotlin para representar e trabalhar com dados JSON em memória, sem usar bibliotecas externas.

## Objetivo

Criar uma biblioteca simples que permita construir, transformar e converter dados JSON para string (formato JSON).

## Funcionalidades

- Representação dos tipos JSON: string, number, boolean, null, array e object
- Serialização com `toJsonString()`
- Métodos `map` e `filter` em `JsonArray`
- Método `filter` em `JsonObject`
- Inferência automática de JSON a partir de objetos Kotlin (strings, números, listas, mapas e data classes)
- Suporte a API HTTP simples usando anotações como `@Mapping` e `@Param`
- Tudo é imutável
- Testes com JUnit
- KDoc em todas as classes públicas

## Exemplo

```kotlin
val obj = JsonObject(
    mapOf(
        "name" to JsonString("Ana"),
        "age" to JsonNumber(22)
    )
)

println(obj.toJsonString())
// {
//    "name": "Ana",
//    "age": 22
// }
```

Também pode ser convertido diretamente um objeto Kotlin com `inferJsonValue`:

```kotlin
data class Person(val name: String, val age: Int)
val json = inferJsonValue(Person("João", 30))
println(json.toJsonString())
// {
//    "name": "João",
//    "age": 30
// }
```

E criar uma pequena API:

```kotlin
@Mapping("/api")
class MyController {
    @Mapping("hello")
    fun sayHello(@Param("name") name: String) = "Olá, $name"
}
```

## Testes

Para correr os testes no terminal:

```
./gradlew test
```

Ou no IntelliJ, clicando no botão de teste ao lado de cada função.