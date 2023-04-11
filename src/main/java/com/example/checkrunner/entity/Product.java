package com.example.checkrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    /**
     * Поле с айди продукта
     */
    private int id;
    /**
     * Поле с названием продукта
     */
    private String name;
    /**
     * Поле с ценой продукта
     */
    private double price;
    /**
     * Поле со статусом продукта
     */
    private String status;
    /**
     * Поле с количеством продукта
     */
    private int amount;

}
