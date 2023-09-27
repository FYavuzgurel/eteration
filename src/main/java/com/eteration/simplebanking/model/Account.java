package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.controller.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id", precision = 18)
    private Long id;

    @Column
    private String owner;

    @Column
    private String accountNumber;

    @Column
    private double balance;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date date;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactions;

    public Account() {
        this.date = new Date();
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.date = new Date();
        this.transactions = new ArrayList<>();
    }

    public TransactionStatus post(Transaction transaction) throws InsufficientBalanceException {
        if (transaction.getIsPositive()) {
            balance += transaction.getAmount();
        } else {
            if (transaction.getAmount() > this.balance) {
                throw new InsufficientBalanceException();
            } else {
                balance -= transaction.getAmount();
            }
        }
        transaction.setAccount(this);
        transactions.add(transaction);
        return new TransactionStatus("OK");
    }

    public void deposit(double amount) {
        try {
            Transaction transaction = new DepositTransaction(amount);
            this.post(transaction);
        } catch (Exception exception) {

        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        Transaction transaction = new WithdrawalTransaction(amount);
        this.post(transaction);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
