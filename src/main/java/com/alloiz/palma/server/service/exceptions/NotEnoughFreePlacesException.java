package com.alloiz.palma.server.service.exceptions;

public class NotEnoughFreePlacesException extends RuntimeException{

    private static String info = "EXCEPTION:";
    private String message;

    public NotEnoughFreePlacesException(String message) {
        this.message = info + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
