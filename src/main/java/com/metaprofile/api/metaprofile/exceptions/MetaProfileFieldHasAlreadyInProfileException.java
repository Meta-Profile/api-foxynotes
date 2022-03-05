package com.metaprofile.api.metaprofile.exceptions;

import com.metaprofile.api.metaprofile.errors.MetaProfileErrorMessage;
import com.metaprofile.api.metaprofile.errors.MetaProfileErrorCode;

import java.text.MessageFormat;

/**
 * Сигнатура мета-поля уже добавлена в профиль
 */
public class MetaProfileFieldHasAlreadyInProfileException extends MetaProfileException {
    /**
     * Сигнатура мета-поля уже добавлена в профиль
     * @param mpfId
     * @param mpId
     */
    public MetaProfileFieldHasAlreadyInProfileException(Long mpfId, Long mpId) {
        super(
                MessageFormat.format(MetaProfileErrorMessage.fieldHasAlreadyInProfile, mpfId, mpId),
                MetaProfileErrorCode.fieldHasAlreadyInProfile
        );
    }
}
