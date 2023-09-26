package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.controller.TransactionStatus;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String owner;
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public TransactionStatus post(Transaction transaction) throws InsufficientBalanceException {
        if (transaction.isPositive()) {
            balance += transaction.getAmount();
        } else {
            if (transaction.getAmount() > this.balance) {
                throw new InsufficientBalanceException();
            } else {
                balance -= transaction.getAmount();
            }
        }
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
}
