# json-lib

Este é um projeto em Kotlin para representar e trabalhar com dados JSON em memória, sem usar bibliotecas externas.

## Objetivo

Criar uma biblioteca simples que permita construir, transformar e converter dados JSON para string (formato JSON).

## Funcionalidades

- Representação dos tipos JSON: string, number, boolean, null, array e object
- Serialização com `toJsonString()`
- Métodos `map` e `filter` em JsonArray
- Método `filter` em JsonObject
- Tudo é imutável
- Testes com JUnit

## Exemplo

```kotlin
val obj = JsonObject(
    mapOf(
        "name" to JsonString("Ana"),
        "age" to JsonNumber(22)
    )
)

println(obj.toJsonString())
// {"name":"Ana","age":22}
```

## Testes

Para correr os testes no terminal:

```
./gradlew test
```

Ou no IntelliJ, clicando no botão de teste ao lado de cada função.

## Nota

Este projeto ainda está incompleto.