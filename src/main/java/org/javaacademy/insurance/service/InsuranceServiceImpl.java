package org.javaacademy.insurance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.insurance.config.InsuranceProperty;
import org.javaacademy.insurance.exception.ContractException;
import org.javaacademy.insurance.model.Contract;
import org.javaacademy.insurance.model.Country;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.service.calc.InsuranceCalcService;
import org.javaacademy.insurance.storage.Archive;
import org.javaacademy.util.Generator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
@Component
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceCalcService insuranceCalcService;
    private final InsuranceProperty insuranceProperty;
    private final Archive archive;

    @Override
    public Contract offerInsurance(BigDecimal coverageAmountInsurance, String fullName, InsuranceType insuranceType) {
        Contract contract = new Contract(Generator.generateNumber(),
                insuranceCalcService.getCostInsurance(coverageAmountInsurance, insuranceType),
                coverageAmountInsurance,
                fullName,
                insuranceProperty.getCountry(),
                insuranceType);
        setCurrency(contract);
        log.info("create contract {}", contract);
        archive.add(contract);
        log.info("add contract to archive, number {}", contract.getNumber());
        return contract;
    }

    @Override
    public Contract paidInsurance(String numberInsurance) {
        Contract contract = archive.findContractByNumber(numberInsurance);
        contract.setPaid(true);
        return contract;
    }

    private void setCurrency(Contract contract) {
        Country country = checkValidCountry(contract);
        contract.setCurrency(country.getCurrency());
    }

    private Country checkValidCountry(Contract contract) {
        return Arrays.stream(Country.values())
                .filter(country -> country.getNameCountry().equals(contract.getCountry()))
                .findAny()
                .orElseThrow(() -> new ContractException(
                        String.format("Error creating contract. "
                                        + "The country  cannot %s be used to create a contract. ",
                                contract.getCountry())));
    }
}
