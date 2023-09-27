package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

public class TransactionStatus {

    private String approvalCode;

    private String status;

    public TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
