package com.marcossousadev.respostas_profissional_spring.services;

import com.marcossousadev.respostas_profissional_spring.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    // camada de armazenamento em memória, repository falso
    List<Product> produtos = new ArrayList<>();
    // regras de negócio
    public List<Product> getProducts(Double filter) {
        if(filter == null){
            return produtos;
        }
        else {
            List<Product> lista_filtrada = produtos.stream().filter(product -> product.getValue_product() == filter).toList();
            return lista_filtrada;
        }
    }
    public Product getProductById(int id){
       Product produtoId = produtos.get(id);
        return produtoId;
    }
    public void createProduct(Product product) {
        produtos.add(product);
        product.setId(produtos.size() - 1);
    }
    public void updateProduct(int id, Product produto) {
        Product productId = produtos.get(id);
        productId.setName(produto.getName());
        productId.setDescription(produto.getDescription());
        productId.setValue_product(produto.getValue_product());
    }
    public void deleteProduct(int id){
       produtos.remove(id);
       for(int i = 0; i < produtos.size(); i++ ){
           produtos.get(i).setId(i);
       }
    }
}
