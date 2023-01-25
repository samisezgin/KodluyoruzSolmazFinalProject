package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.MaleTicketLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MaleTicketLimitAdvice {

    @ResponseBody
    @ExceptionHandler(MaleTicketLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String maleTicketLimitHandler(MaleTicketLimitException exception)
    {
        return exception.getMessage();
    }
}
