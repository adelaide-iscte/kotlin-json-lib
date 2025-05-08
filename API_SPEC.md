# API_SPEC.md

## Objetivo

A `json-lib` é uma biblioteca em Kotlin que permite representar, transformar e gerar JSON diretamente a partir de objetos Kotlin. Tudo é feito em memória, sem depender de bibliotecas externas.

---

## Modelo de Dados

A estrutura base da biblioteca é a classe selada `JsonValue`, da qual derivam os seguintes tipos:

### Tipos Simples
- `JsonString(value: String)`
- `JsonNumber(value: Number)` — pode ser `Int`, `Double`, etc.
- `JsonBoolean(value: Boolean)`
- `JsonNull` — é um objeto singleton

### Tipos Compostos
- `JsonArray(elements: List<JsonValue>)`
- `JsonObject(properties: Map<String, JsonValue>)`

Todos os tipos são imutáveis e implementam a função `toJsonString()` para gerar o JSON correspondente.

---

## Serialização

Cada valor JSON pode ser convertido para `String` através da função:

```kotlin
fun toJsonString(): String
```

Exemplos:

```kotlin
JsonString("hello").toJsonString()      // ""hello""
JsonNumber(42).toJsonString()           // "42"
JsonNull.toJsonString()                 // "null"
JsonArray(listOf(JsonNumber(1))).toJsonString() // "[1]"
```

---

## Operações de Transformação

### JsonArray

```kotlin
fun map(transform: (JsonValue) -> JsonValue): JsonArray
fun filter(predicate: (JsonValue) -> Boolean): JsonArray
```

### JsonObject

```kotlin
fun filter(predicate: (String, JsonValue) -> Boolean): JsonObject
```

Essas operações devolvem sempre uma nova instância, sem alterar o original.

---
## Inferência Dinâmica (Fase 2)

A biblioteca inclui uma função extra para converter objetos Kotlin comuns automaticamente em `JsonValue`:

```kotlin
fun inferJsonValue(any: Any?): JsonValue
```

Esta função aceita:
- Strings → `JsonString`
- Números (Int, Double, etc.) → `JsonNumber`
- Booleanos → `JsonBoolean`
- `null` → `JsonNull`
- Listas → `JsonArray`
- Mapas com chaves `String` → `JsonObject`
- Data classes (recursivamente) → `JsonObject`

Se o tipo não for suportado, a função lança `IllegalArgumentException`.

---
## Exposição via HTTP GET (Fase 3)

A biblioteca também permite expor métodos Kotlin como endpoints HTTP GET, semelhante ao estilo de frameworks como Spring.

### Anotações

São usadas duas anotações para definir a estrutura dos endpoints:

- `@Mapping(path: String)`: Aplica-se a classes (definindo o prefixo) e a métodos (definindo o sufixo).
- `@Param(name: String)`: Aplica-se aos parâmetros da função, indicando o nome esperado na query string.

### Exemplo de Controlador

```kotlin
@Mapping("/api")
class TestController {

    @Mapping("/hello")
    fun hello(): String = "Olá, mundo!"

    @Mapping("/eco")
    fun echo(@Param("msg") mensagem: String): String = "Eco: $mensagem"

    @Mapping("/soma")
    fun soma(@Param("a") a: String, @Param("b") b: String): Int =
        a.toInt() + b.toInt()
}
```

### Inicialização

```kotlin
val api = GetJson(TestController::class)
api.start(8080)
```

Depois disso, é possível fazer chamadas HTTP GET como:

```
GET /api/hello
GET /api/eco?msg=oi
GET /api/soma?a=2&b=3
```

### Serialização Automática

Os valores de retorno são convertidos automaticamente para `JsonValue` com a função `inferJsonValue`, o que garante compatibilidade com o formato JSON.

---

## Testes

Cada tipo e funcionalidade está acompanhado de testes com JUnit (utilizando `kotlin.test` e `org.junit.jupiter`). Os testes validam a serialização e o comportamento das transformações.

---

## Documentação

Todos os tipos e funções públicos estão documentados com KDoc para facilitar a utilização e compreensão da biblioteca.

---
