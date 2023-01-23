package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TicketNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ticketNotFoundHandler(TicketNotFoundException exception)
    {
        return exception.getMessage();
    }
}
