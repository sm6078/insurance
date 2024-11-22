package org.javaacademy.insurance.service.calc;

import org.javaacademy.insurance.model.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    BigDecimal getCostInsurance(BigDecimal coverageAmountInsurance, InsuranceType insuranceType);

    default BigDecimal calculateCostInsurance(BigDecimal coverageAmountInsurance,
                                              String insuranceCoefficient, long basisCost) {
        return (coverageAmountInsurance.multiply(new BigDecimal(insuranceCoefficient)))
                .add(BigDecimal.valueOf(basisCost));
    }
}
