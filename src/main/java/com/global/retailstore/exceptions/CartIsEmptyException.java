package com.global.retailstore.exceptions;

public class CartIsEmptyException extends  RuntimeException{

    public CartIsEmptyException(String message){
        super(message);
    }

}
