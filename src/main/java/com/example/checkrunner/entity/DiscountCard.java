package com.example.checkrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountCard {
    /**
     * Поле с айди карты
     */
    private int id;
    /**
     * Поле с кодом карты
     */
    private String code;
    /**
     * Поле с размером скидки карты
     */
    private int discount;
}
