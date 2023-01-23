package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.TicketListOverflowException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TicketListOverflowAdvice {
    @ResponseBody
    @ExceptionHandler(TicketListOverflowException.class)
    @ResponseStatus(HttpStatus.OK)
    String ticketListOverflowHandler(TicketListOverflowException exception) {
        return exception.getMessage();
    }
}


