package org.javaacademy.insurance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Country {
    JAPAN("Japan"),
    BRAZIL("Brazil");

    private final String nameCountry;
}
