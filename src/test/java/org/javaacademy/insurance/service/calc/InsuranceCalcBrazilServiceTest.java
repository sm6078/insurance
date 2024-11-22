package org.javaacademy.insurance.service.calc;

import org.javaacademy.insurance.model.InsuranceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("brazil")
public class InsuranceCalcBrazilServiceTest {
    @Autowired
    private InsuranceCalcService insuranceCalcService;

    @Test
    @DisplayName("Получаем расчет стоимости страховки при грабеже")
    public void getCalculateCostRobberyInsurance() {
        BigDecimal coverageAmountInsurance = BigDecimal.valueOf(50_000);
        BigDecimal result = insuranceCalcService.getCostInsurance(coverageAmountInsurance, InsuranceType.ROBBERY);
        BigDecimal expected = BigDecimal.valueOf(2800);
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    @DisplayName("Получаем расчет стоимости медстраховки")
    public void getCalculateCostMedicalInsurance() {
        BigDecimal coverageAmountInsurance = BigDecimal.valueOf(200_000);
        BigDecimal result = insuranceCalcService.getCostInsurance(coverageAmountInsurance, InsuranceType.HEALTH);
        BigDecimal expected = BigDecimal.valueOf(6800);
        assertEquals(0, expected.compareTo(result));
    }
}
