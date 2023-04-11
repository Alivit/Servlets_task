package com.example.checkrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
