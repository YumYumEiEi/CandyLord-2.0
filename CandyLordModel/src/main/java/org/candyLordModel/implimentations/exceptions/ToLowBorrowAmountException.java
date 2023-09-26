package org.candyLordModel.implimentations.exceptions;

public class ToLowBorrowAmountException extends RuntimeException {
    public ToLowBorrowAmountException(String message) {
        super(message);
    }
}
