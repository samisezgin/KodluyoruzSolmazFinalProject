package com.samisezgin.finalproject.exceptions;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String msg)
    {
        super(msg);
    }
}
