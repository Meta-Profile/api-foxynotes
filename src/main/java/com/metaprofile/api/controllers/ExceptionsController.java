package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.exceptions.NotDeniedMetaProfileException;
import com.metaprofile.api.exceptions.NotFoundMetaProfileException;
import com.metaprofile.api.model.Node;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(NotFoundMetaProfileException.class)
    public ResponseEntity MetaProfileNotFound(){
        return new ControllerResponse<Node>("Meta profile not found", HttpStatus.NOT_FOUND).response();
    }


    @ExceptionHandler(NotDeniedMetaProfileException.class)
    public ResponseEntity MetaProfileNotDenied(){
        return new ControllerResponse<Node>("Meta profile is not denied", HttpStatus.FORBIDDEN).response();
    }

}
