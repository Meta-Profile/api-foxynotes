package com.metaprofile.api.core.controllers;

import com.metaprofile.api.core.ControllerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {


    @ExceptionHandler(Exception.class)
    public ResponseEntity EceptionError(Exception ex){
        return new ControllerResponse<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST).response();
    }

}
