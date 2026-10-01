package com.marcossousadev.respostas_profissional_spring.controller;

import com.marcossousadev.respostas_profissional_spring.domain.Product;
import com.marcossousadev.respostas_profissional_spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// como deixar as respostas da nossa API de uma forma mais profissional?
// usamos o ResponseEntity do Spring

// para quê serve o ResponseEntity?
// serve para deixar nossas respostas mais profissionais e de forma mais customizada
@RestController()
@RequestMapping("/product")
public class ProductController {
    // se queremos usar o ResponseEntity para ter uma resposta mais customizável, temos que sempre retornar ele!
    // ele pergunta qual tipo queremos retornar, atráves do que chamamos de Generics que usa parametrização!

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getUProductByIdTest(@PathVariable("id") int id) {
        if(id > 2 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(id);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
    // vamos supor que eu quero passar um head para quem tá consumindo minha API
    @GetMapping
    public ResponseEntity<String> getProducts() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("custom-header", "head customizavel");

        return new ResponseEntity<>("tudo ok", httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Product>> getProdutos(@RequestParam(value = "valorProduto", required = false) Double filter){
        List<Product> produtos = productService.getProducts(filter);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtos);
    }

    // retornando um product por exemplo
    @GetMapping("/buscar-produto/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product produto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produto);
    }
    @PostMapping("/criar-produto")
    public ResponseEntity<String> createProduct(@RequestBody Product body){
        productService.createProduct(body);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
    }
    @PatchMapping("/atualizar-produto/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product body, @PathVariable int id) {
        productService.updateProduct(id, body);
        return ResponseEntity.status(HttpStatus.OK).body("Produto alterado com sucesso!");
    }
    @DeleteMapping("/deletar-produto/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
    }
}
