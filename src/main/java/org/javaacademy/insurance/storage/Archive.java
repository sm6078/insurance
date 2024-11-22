package org.javaacademy.insurance.storage;

import lombok.Getter;
import org.javaacademy.insurance.exception.NotExistArchiveException;
import org.javaacademy.insurance.model.Contract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Getter
@Component
public class Archive {
    private final Map<String, Contract> contracts = new HashMap<>();

    public boolean add(Contract contract) {
        return !Objects.equals(contracts.put(contract.getNumber(), contract), contract);
    }

    public Contract findContract(String numberInsurance) throws NotExistArchiveException {
        return Optional.ofNullable(contracts.get(numberInsurance))
                .orElseThrow(() -> new NotExistArchiveException(
                        String.format("Contract number: %s does not exist.", numberInsurance)));
    }
}
