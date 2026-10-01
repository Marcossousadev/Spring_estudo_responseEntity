# ResponseEntity no Spring Boot

Este repositório foi criado para estudar o **ResponseEntity** no Spring Boot e entender como podemos ter maior controle sobre as respostas enviadas pela API.

## O que é ResponseEntity?

O `ResponseEntity` é uma classe do Spring que representa a **resposta HTTP** enviada pela API.

Com ele, podemos controlar:

* **Status HTTP** da resposta
* **Corpo (body)** da resposta
* **Headers** da resposta

Dessa forma, em vez de retornar apenas um objeto, podemos definir exatamente como a API deve responder ao cliente.

## Exemplo básico

```java
@GetMapping("/{id}")
public ResponseEntity<Task> getTask(@PathVariable int id) {

    Task task = taskService.getTask(id);

    return ResponseEntity.ok(task);
}
```

Nesse exemplo:

```java
ResponseEntity.ok(task);
```

retorna:

```http
200 OK
```

e o objeto `task` é enviado no corpo da resposta.

## Retornando outros status HTTP

Também podemos utilizar outros métodos do `ResponseEntity`:

```java
return ResponseEntity.ok(task);
```

Retorna **200 OK**.

```java
return ResponseEntity.notFound().build();
```

Retorna **404 Not Found**.

```java
return ResponseEntity.badRequest().build();
```

Retorna **400 Bad Request**.

```java
return ResponseEntity.status(201).body(task);
```

Retorna **201 Created** com o objeto `task` no corpo da resposta.

## Por que utilizar ResponseEntity?

O `ResponseEntity` é útil quando precisamos ter mais controle sobre a resposta da API.

Por exemplo, podemos verificar se uma tarefa existe:

```java
@GetMapping("/{id}")
public ResponseEntity<Task> getTask(@PathVariable int id) {

    Task task = taskService.getTask(id);

    if (task == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(task);
}
```

Assim, quando a tarefa existe, retornamos:

```http
200 OK
```

Quando não existe:

```http
404 Not Found
```

## Resumo

O `ResponseEntity` permite controlar a resposta HTTP da API, possibilitando definir:

**Status + Headers + Body**

Ele é bastante utilizado em APIs REST desenvolvidas com Spring Boot.
