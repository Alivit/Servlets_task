package com.example.checkrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Promotional {
    /**
     * Поле хранит информацию о продукте
     */
    private Product product;
    /**
     * Поле хранит новую цену продукта с учётом скидки
     */
    private double newPrice;


}
