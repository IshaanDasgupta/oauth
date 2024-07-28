package com.Bank.resourceServer.Exception;

public class DuplicateAccountException extends RuntimeException{
    public DuplicateAccountException(String message) {
        super(message);
    }
}
