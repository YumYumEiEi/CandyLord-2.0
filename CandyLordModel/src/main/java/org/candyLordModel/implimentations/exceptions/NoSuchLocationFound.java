package org.candyLordModel.implimentations.exceptions;

public class NoSuchLocationFound extends RuntimeException {

    public NoSuchLocationFound(IllegalArgumentException e, String message){
        super(message, e);
    }
}
