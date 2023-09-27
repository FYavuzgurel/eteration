package com.eteration.simplebanking.model;


import javax.persistence.Entity;

@Entity
public class BillPaymentTransaction extends Transaction {

    private String payee;
    private String phoneNumber;

    public BillPaymentTransaction() {
        this("", "", 0);
    }

    public BillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(amount, false, "BillPaymentTransaction");
        this.payee = payee;
        this.phoneNumber = phoneNumber;
    }
}


