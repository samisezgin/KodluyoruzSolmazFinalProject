package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.EmptyTicketListException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmptyTicketListExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(EmptyTicketListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String emptyTicketListHandler(EmptyTicketListException exception)
    {
        return exception.getMessage();
    }
}
