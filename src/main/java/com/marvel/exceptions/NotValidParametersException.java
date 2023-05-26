package com.marvel.exceptions;

public class NotValidParametersException extends RuntimeException{

    public NotValidParametersException() {
    }

    public NotValidParametersException(String s) {
        super(s);
    }
}
