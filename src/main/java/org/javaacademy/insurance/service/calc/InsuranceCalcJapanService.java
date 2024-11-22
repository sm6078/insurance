package org.javaacademy.insurance.service.calc;

import org.javaacademy.insurance.model.InsuranceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("japan")
public class InsuranceCalcJapanService implements InsuranceCalcService {
    private static final Long BASIS_ROBBERY_INSURANCE_COST = 10_000L;
    private static final Long BASIS_HEALTH_INSURANCE_COST = 12_000L;
    @Value("${app.coefficient.robbery}")
    private String coefficientRobbery;
    @Value("${app.coefficient.health}")
    private String coefficientHealth;

    @Override
    public BigDecimal getCostInsurance(BigDecimal coverageAmountInsurance, InsuranceType insuranceType) {
        if (InsuranceType.ROBBERY.equals(insuranceType)) {
            return calculateCostInsurance(coverageAmountInsurance, coefficientRobbery,
                    BASIS_ROBBERY_INSURANCE_COST);
        }
        return calculateCostInsurance(coverageAmountInsurance, coefficientHealth,
                BASIS_HEALTH_INSURANCE_COST);
    }
}
