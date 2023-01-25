package com.samisezgin.finalproject.exceptions;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String msg)
    {
        super(msg);
    }
}
