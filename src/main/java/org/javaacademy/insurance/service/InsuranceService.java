package org.javaacademy.insurance.service;

import org.javaacademy.insurance.model.Contract;
import org.javaacademy.insurance.model.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceService {

    Contract offerInsurance(BigDecimal coverageAmountInsurance, String fullName, InsuranceType insuranceType);

    Contract paidInsurance(String numberInsurance);
}
