package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable(value = "accountNumber") String accountNumber) {
        try {
            Account account = accountService.findAccount(accountNumber);
            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable(value = "accountNumber") String accountNumber, @RequestBody DepositTransaction depositTransaction) throws InsufficientBalanceException {
        return this.newTransaction(accountNumber, depositTransaction);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable(value = "accountNumber") String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        return this.newTransaction(accountNumber, withdrawalTransaction);
    }

    @PostMapping("/new")
    public ResponseEntity newAccount(@RequestBody Account account) {
        accountService.save(account);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<TransactionStatus> newTransaction(String accountNumber, Transaction transaction) throws InsufficientBalanceException {
        try {
            Account account = accountService.findAccount(accountNumber);
            TransactionStatus status = account.post(transaction);
            accountService.update(account);
            status.setApprovalCode(transaction.getApprovalCode());
            return ResponseEntity.ok(status);
        } catch (AccountNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}