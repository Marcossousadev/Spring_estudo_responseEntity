package com.marcossousadev.respostas_profissional_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private int id;
    private String name;
    private String description;
    private double value_product;

    public Product(String name, String description, double value_product) {
        this.name = name;
        this.description = description;
        this.value_product = value_product;
    }
}
