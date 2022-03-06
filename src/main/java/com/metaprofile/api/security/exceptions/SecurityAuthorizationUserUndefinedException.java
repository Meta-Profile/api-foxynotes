package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;
import com.metaprofile.api.security.SecurityErrorMessage;

import java.text.MessageFormat;

public class SecurityAuthorizationUserUndefinedException extends CoreException {
    public SecurityAuthorizationUserUndefinedException() {
        super(
                SecurityErrorMessage.userAuthorizationUndefined,
                1001
        );
    }
}
