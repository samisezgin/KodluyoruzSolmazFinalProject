package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.EmptyTicketListException;
import com.samisezgin.finalproject.exceptions.MaleTicketLimitException;
import com.samisezgin.finalproject.exceptions.TicketListOverflowException;
import com.samisezgin.finalproject.exceptions.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TicketExceptionsAdvice {
    @ResponseBody
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ticketNotFoundHandler(TicketNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TicketListOverflowException.class)
    @ResponseStatus(HttpStatus.OK)
    String ticketListOverflowHandler(TicketListOverflowException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmptyTicketListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String emptyTicketListHandler(EmptyTicketListException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MaleTicketLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String maleTicketLimitHandler(MaleTicketLimitException exception) {
        return exception.getMessage();
    }

}
