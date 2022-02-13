package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.security.models.UserRoleName;

public class AuthRoleNotFoundException extends AuthException{
    public AuthRoleNotFoundException(UserRoleName roleName){
        super("User role " + roleName.toString() + " not found!");
    }
}