package com.samisezgin.finalproject.exceptions;

public class EmptyTicketListException extends RuntimeException {
    public EmptyTicketListException(String msg) {
        super(msg);
    }
}
