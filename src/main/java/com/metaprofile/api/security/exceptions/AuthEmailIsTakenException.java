package com.metaprofile.api.security.exceptions;

public class AuthEmailIsTakenException extends AuthException{
    public AuthEmailIsTakenException(String email){
        super("Email " + email + " is already taken");
    }
}
