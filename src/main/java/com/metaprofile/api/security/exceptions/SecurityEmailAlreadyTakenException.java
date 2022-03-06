package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;
import com.metaprofile.api.security.SecurityErrorMessage;

import java.text.MessageFormat;

public class SecurityEmailAlreadyTakenException extends CoreException {
    public SecurityEmailAlreadyTakenException(String email) {
        super(
                MessageFormat.format(SecurityErrorMessage.emailIsAlreadyTaken, email),
                1001
        );
    }
}
