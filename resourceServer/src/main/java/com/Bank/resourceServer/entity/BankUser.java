package com.Bank.resourceServer.entity;

import com.Bank.resourceServer.Exception.DuplicateAccountException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "BankUser.findAll", query = "SELECT u FROM BankUser u")
public class BankUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userID;
    private String name;
    private Long mobileNumber;
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    public static final String CURRENT_ACCOUNT = "current";
    public static final String SAVINGS_ACCOUNT = "savings";

    // Default constructor
    public BankUser() {}

    // Constructors with parameters
    public BankUser(int userID, String name, Long mobileNumber, String email, Address address) {
        this.userID = userID;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
    }

    public BankUser(int userID, String name, Long mobileNumber, String email) {
        this.userID = userID;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public BankUser(int userID, String name, Long mobileNumber) {
        this.userID = userID;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public BankUser(String name, String accountType){
        this.name = name;
        Account account = new Account(accountType, this);
        this.accounts.add(account);
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public BankUser(int userID, String name, Long mobileNumber, String email, Address address, List<Account> accounts) {
        this.userID = userID;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.accounts = accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // Methods to add accounts
    public void addAccount(String accountType) throws DuplicateAccountException {
        if (accountType.equalsIgnoreCase(CURRENT_ACCOUNT) && hasCurrentAccount()) {
            throw new DuplicateAccountException("A current account already exists. You can only have one current account.");
        } else if (accountType.equalsIgnoreCase(SAVINGS_ACCOUNT) && hasSavingsAccount()) {
            throw new DuplicateAccountException("A savings account already exists. You can only have one savings account.");
        } else {
            Account account = new Account(accountType, this);
            this.accounts.add(account);
        }
    }

    public boolean hasCurrentAccount() {
        for (Account account : accounts) {
            if (account.getAccountType().equalsIgnoreCase(CURRENT_ACCOUNT)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSavingsAccount() {
        for (Account account : accounts) {
            if (account.getAccountType().equalsIgnoreCase(SAVINGS_ACCOUNT)) {
                return true;
            }
        }
        return false;
    }

    // Methods to get account details
    public Account getAccount(Long accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public Account setAccountBalance(Long accountId, int balanceToSet) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                account.setAccountBalance(balanceToSet);
            }
        }
        return null;
    }

    public String getAccountBalance(Long accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account.getAccountBalance();
            }
        }
        return null;
    }

    public List<Transaction> getUserTransactions(Long accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account.getTransaction();
            }
        }
        return null; // Or throw an exception if account not found
    }
}

