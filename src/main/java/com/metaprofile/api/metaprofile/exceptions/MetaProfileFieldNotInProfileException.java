package com.metaprofile.api.metaprofile.exceptions;

import com.metaprofile.api.metaprofile.errors.MetaProfileErrorMessage;
import com.metaprofile.api.metaprofile.errors.MetaProfileErrorCode;

import java.text.MessageFormat;

/**
 * Сигнатура мета-поля не добавлена в мета-профиль
 */
public class MetaProfileFieldNotInProfileException extends MetaProfileException {
    public MetaProfileFieldNotInProfileException(Long mpfId, Long mpId) {
        super(
                MessageFormat.format(MetaProfileErrorMessage.fieldNotFoundInProfile, mpfId, mpId),
                MetaProfileErrorCode.fieldNotInProfile
        );
    }
}
