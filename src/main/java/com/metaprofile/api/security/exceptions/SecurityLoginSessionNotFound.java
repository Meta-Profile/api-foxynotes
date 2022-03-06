package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;
import com.metaprofile.api.security.SecurityErrorMessage;

public class SecurityLoginSessionNotFound extends CoreException {
    public SecurityLoginSessionNotFound(){
        super(SecurityErrorMessage.loginSessionNotFound, 404);
    }
}
