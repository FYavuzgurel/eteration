package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

// This class is a place holder you can change the complete implementation

@Entity
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column
    private Long id;

    @Column
    private String approvalCode;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column
    private double amount;
    @JsonIgnore
    @Column
    private boolean isPositive; // true ise para yatırma işlemi - false ise para çekme işlemi

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    public Transaction() {

    }

    public Transaction(double amount) {
        this(amount, true, "");
        this.approvalCode=generateApprovalCode();
    }

    public Transaction(double amount, boolean isPositive, String type) {
        this.amount = amount;
        this.isPositive = isPositive;
        this.date = new Date();
        this.type = type;
        this.approvalCode=generateApprovalCode();
    }

    public static String generateApprovalCode() {
        UUID uuid = UUID.randomUUID();
        String approvalCode = uuid.toString().substring(0, 32);
        return approvalCode;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(boolean positive) {
        isPositive = positive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
