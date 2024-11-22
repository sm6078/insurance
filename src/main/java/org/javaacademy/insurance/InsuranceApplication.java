package org.javaacademy.insurance;

import org.javaacademy.insurance.model.Contract;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.service.InsuranceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class InsuranceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InsuranceApplication.class, args);
        InsuranceService insuranceService = context.getBean(InsuranceService.class);
        Contract contract = insuranceService.offerInsurance(BigDecimal.TEN,
                "Иванов Сергей", InsuranceType.ROBBERY);
        System.out.println(contract);
        context.close();
    }
}
