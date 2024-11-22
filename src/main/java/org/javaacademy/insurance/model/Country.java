package org.javaacademy.insurance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Country {
    JAPAN("Japan", "JPY"),
    BRAZIL("Brazil", "BRL");

    private final String nameCountry;
    private final String currency;
}
