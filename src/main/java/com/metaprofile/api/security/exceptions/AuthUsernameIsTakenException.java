package com.metaprofile.api.security.exceptions;

public class AuthUsernameIsTakenException extends AuthException{
    public AuthUsernameIsTakenException(String username){
        super("Username " + username + " is already taken");
    }
}
