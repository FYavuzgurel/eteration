package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;

// This class is a place holder you can change the complete implementation
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount, true);
    }
}
