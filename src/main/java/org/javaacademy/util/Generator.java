package org.javaacademy.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class Generator {
    public String generateNumber() {
        return UUID.randomUUID().toString();
    }
}
