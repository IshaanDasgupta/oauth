package com.Bank.resourceServer.Response;
import com.Bank.resourceServer.entity.BankUser;

public class TransferResponse {
    private String message;
    private BankUser userAccount;

    public TransferResponse(String message, BankUser userAccount) {
        this.message = message;
        this.userAccount = userAccount;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankUser getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(BankUser userAccount) {
        this.userAccount = userAccount;
    }
}
