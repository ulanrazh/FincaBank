package com.company.exceptions;

public class NoMoneyException extends RuntimeException{
    public NoMoneyException() {
    }

    public NoMoneyException(String message) {
        super(message);
    }
}
