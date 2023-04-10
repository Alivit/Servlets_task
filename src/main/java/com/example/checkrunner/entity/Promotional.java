package com.example.checkrunner.entity;

import lombok.Data;

@Data
public class Promotional {
    /**
     * Поле хранит новую цену продукта с учётом скидки
     */
    private double newPrice;
    /**
     * Поле хранит информацию о продукте
     */
    private Product product;

}
