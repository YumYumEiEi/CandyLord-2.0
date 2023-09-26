package org.candyLordModel.implimentations.exceptions;

public class ToMuchPayBackException extends RuntimeException {
    public ToMuchPayBackException(String message){
        super(message);
    }
}
