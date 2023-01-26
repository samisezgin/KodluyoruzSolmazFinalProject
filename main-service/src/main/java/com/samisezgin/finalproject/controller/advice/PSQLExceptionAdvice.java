package com.samisezgin.finalproject.controller.advice;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PSQLExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userEmailAlreadyExistsHandler(PSQLException exception)
    {
        var errorCode= exception.getSQLState();
        if(errorCode.equals("23505"))
        {
            return "Bu email ile kayıtlı bir kullanıcı zaten mevcut!";
        }
        return exception.getMessage();
    }
}
