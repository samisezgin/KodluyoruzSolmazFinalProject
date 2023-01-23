package com.samisezgin.finalproject.controller.advice;

import com.samisezgin.finalproject.exceptions.VoyageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class VoyageNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(VoyageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String voyageNotFoundHandler(VoyageNotFoundException exception)
    {
        return exception.getMessage();
    }
}
