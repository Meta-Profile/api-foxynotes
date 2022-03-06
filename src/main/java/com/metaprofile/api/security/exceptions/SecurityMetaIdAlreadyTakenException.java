package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;
import com.metaprofile.api.security.SecurityErrorMessage;

import java.text.MessageFormat;

public class SecurityMetaIdAlreadyTakenException extends CoreException {
    public SecurityMetaIdAlreadyTakenException(String email) {
        super(
                MessageFormat.format(SecurityErrorMessage.metaIdIsAlreadyTaken, email),
                1001
        );
    }
}
