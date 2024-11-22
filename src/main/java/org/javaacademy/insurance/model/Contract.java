package org.javaacademy.insurance.model;



import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * класс Страховой договор
 */

@ToString
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contract {
    @EqualsAndHashCode.Include
    private final String number;
    private final BigDecimal costInsurance;
    private final BigDecimal coverageAmountInsurance;
    private final String currency;
    private final String fullName;
    private final String country;
    private final InsuranceType insuranceType;
    @Setter
    private boolean isPaid = false;
}
