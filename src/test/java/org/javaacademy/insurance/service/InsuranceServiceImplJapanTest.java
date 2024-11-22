package org.javaacademy.insurance.service;

import lombok.Cleanup;
import org.javaacademy.insurance.model.Contract;
import org.javaacademy.insurance.model.Country;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.service.calc.InsuranceCalcService;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.util.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("japan")
public class InsuranceServiceImplJapanTest {

    //private final static String TEST_FULL_NAME = "Иванов Иван Иванович";
    //private final static String TEST_NUMBER = "Один";


    @Autowired
    private InsuranceService insuranceService;

    @MockBean
    private Archive archive;

    @MockBean
    private InsuranceCalcService insuranceCalcService;

    @Test
    @DisplayName("Получаем предложение по страховке при грабеже")
    public void getInsuranceOfferRobberyTypeSuccess() {
        String fullName = "Иванов Иван Иванович";
        String number = "001";
        BigDecimal coverageAmountInsurance = valueOf(1_000_000);
        InsuranceType insuranceType = InsuranceType.ROBBERY;
        @Cleanup
        var numberGeneratorMockedStatic = Mockito.mockStatic(Generator.class);
        Mockito.when(Generator.generateNumber()).thenReturn(number);
        Mockito.when(insuranceCalcService.getCostInsurance(any(), any())).thenReturn(valueOf(20_000));
        Contract expectedContract = new Contract(
                number,
                valueOf(20_000),
                coverageAmountInsurance,
                fullName,
                Country.JAPAN.getNameCountry(),
                insuranceType);
        expectedContract.setPaid(false);
        Contract resultContract = insuranceService.offerInsurance(coverageAmountInsurance,
                fullName, insuranceType);
        Assertions.assertEquals(expectedContract, resultContract);
    }

    @Test
    @DisplayName("Получаем предложение стоимости медицинской страховки")
    public void getInsuranceOfferMedicalTypeSuccess() {
        String fullName = "Иванов Иван Иванович";
        String number = "001";
        BigDecimal coverageAmountInsurance = valueOf(10_000_000);
        InsuranceType insuranceType = InsuranceType.HEALTH;
        @Cleanup
        var numberGeneratorMockedStatic = Mockito.mockStatic(Generator.class);
        Mockito.when(Generator.generateNumber()).thenReturn(number);
        Mockito.when(insuranceCalcService.getCostInsurance(any(), any())).thenReturn(valueOf(162_000));
        Contract expectedContract = new Contract(
                number,
                valueOf(162_000),
                coverageAmountInsurance,
                fullName,
                Country.JAPAN.getNameCountry(),
                insuranceType);
        expectedContract.setPaid(false);
        Contract resultContract = insuranceService.offerInsurance(coverageAmountInsurance,
                fullName, insuranceType);
        Assertions.assertEquals(expectedContract, resultContract);
    }

    @Test
    @DisplayName("Опалата страховки")
    public void payForInsuranceContractSuccess() {
        String number = "001";
        String fullName = "Иванов Иван Иванович";
        Contract unpaidInsuranceContract = new Contract(
                number,
                valueOf(165_000),
                valueOf(10_000_000),
                fullName,
                Country.JAPAN.getNameCountry(),
                InsuranceType.HEALTH);
        unpaidInsuranceContract.setPaid(false);
        Contract expectedInsuranceContract = new Contract(
                number,
                valueOf(165_000),
                valueOf(10_000_000),
                fullName,
                Country.JAPAN.getNameCountry(),
                InsuranceType.HEALTH);
        expectedInsuranceContract.setPaid(true);
        Mockito.when(archive.findContractByNumber(any())).thenReturn(unpaidInsuranceContract);
        Contract resultInsuranceContract = insuranceService.paidInsurance(archive, number);
        Assertions.assertEquals(expectedInsuranceContract, resultInsuranceContract);
    }
}
