package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.BookingNotFoundException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BookingExceptionsAdvice {
    @ResponseBody
    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookingNotFoundHandler(BookingNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String indexOutOfBoundsHandler(IndexOutOfBoundsException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String mappingHandler() {
        return "Seferin tarih ve saatini doğru girdiğinizden emin olun.";
    }

}
