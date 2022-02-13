package com.metaprofile.api.security.exceptions;

public class AuthException extends RuntimeException{
    public AuthException(String message){
        super(message);
    }
    public AuthException(){
        super("Auth undefined error");
    }
}
