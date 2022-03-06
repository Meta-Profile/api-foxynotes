package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;

public class SecurityException extends CoreException {
    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Integer code) {
        super(message, code);
    }
}
