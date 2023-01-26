package com.samisezgin.finalproject.controller.advice;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ModelMapperMappingAdvice {
    @ResponseBody
    @ExceptionHandler(MappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String mappingHandler()
    {
        return "Seferin tarih ve saatini doğru girdiğinizden emin olun.";
    }
}
