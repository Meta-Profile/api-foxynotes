package com.metaprofile.api.security.exceptions;

import com.metaprofile.api.core.exceptions.CoreException;
import com.metaprofile.api.security.SecurityErrorMessage;
import com.metaprofile.api.security.models.UserRoleName;

import java.text.MessageFormat;

public class SecurityRoleNotFoundException extends CoreException {
    public SecurityRoleNotFoundException(UserRoleName role) {
        super(
                MessageFormat.format(SecurityErrorMessage.roleNotFound, role),
                404
        );
    }
}
