package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// This class is a place holder you can change the complete implementation

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 18)
    private Long id;
    @Column
    private Date date;
    @Column
    private double amount;
    @Column
    private boolean isPositive; // true ise deposit tran. false ise withdraw  tran.

    public Transaction(double amount) {
        this(amount, true);
    }

    public Transaction(double amount, boolean isPositive) {
        this.amount = amount;
        this.isPositive = isPositive;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }
}
