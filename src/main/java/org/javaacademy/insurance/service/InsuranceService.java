package org.javaacademy.insurance.service;

import org.javaacademy.insurance.exception.NotExistArchiveException;
import org.javaacademy.insurance.model.Contract;
import org.javaacademy.insurance.model.InsuranceType;
import org.javaacademy.insurance.storage.Archive;

import java.math.BigDecimal;

public interface InsuranceService {

    Contract offerInsurance(BigDecimal coverageAmountInsurance, String fullName, InsuranceType insuranceType);

    default void addArchive(Archive archive, Contract contract) {
        archive.add(contract);
    }

    default Contract paidInsurance(Archive archive, String numberInsurance) throws NotExistArchiveException {
        Contract contract = archive.findContractByNumber(numberInsurance);
        contract.setPaid(true);
        return contract;

    }
}
