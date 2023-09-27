package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

// This class is a place holder you can change the complete implementation
@Entity
public class DepositTransaction extends Transaction {
    public DepositTransaction() {
        this(0);
    }

    public DepositTransaction(double amount) {
        super(amount, true, "DepositTransaction");
    }
}
