package com.alloiz.palma.server.service.exceptions;

public class RoomTypeNotFoundException extends RuntimeException {

    private static String info = "EXCEPTION:";
    private String message;

    public RoomTypeNotFoundException(String message) {
        this.message = info + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
