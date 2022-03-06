package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;
import com.metaprofile.api.security.SecurityErrorMessage;

public class SecurityUserNotFound extends CoreException {
    public SecurityUserNotFound(){
        super(SecurityErrorMessage.userNotFound);
    }
}
