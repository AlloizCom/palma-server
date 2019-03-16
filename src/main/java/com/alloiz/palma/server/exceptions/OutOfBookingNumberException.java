package com.alloiz.palma.server.exceptions;

public class OutOfBookingNumberException extends RuntimeException {
    public OutOfBookingNumberException() {
        super("Book is out of allowed book number range");
    }
}
