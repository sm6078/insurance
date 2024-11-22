package org.javaacademy.insurance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
    JPY("JPY"),
    BRL("BRL");

    private final String nameCurrency;
}

