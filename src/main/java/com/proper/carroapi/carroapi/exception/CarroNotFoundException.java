package com.proper.carroapi.carroapi.exception;

public class CarroNotFoundException extends RuntimeException{

    public CarroNotFoundException(String message) {
        super(message);
    }
}
