package com.metaprofile.api.metaprofile.exceptions;

import com.metaprofile.api.metaprofile.errors.MetaProfileErrorMessage;
import com.metaprofile.api.metaprofile.errors.MetaProfileErrorCode;

import java.text.MessageFormat;

/**
 * Данные мета поля запрещены для редактирования
 */
public class MetaProfileDataChangeForbiddenException extends MetaProfileException {
    public MetaProfileDataChangeForbiddenException(Long mpdId, Long mpId) {
        super(
                MessageFormat.format(MetaProfileErrorMessage.dataChangeForbidden, mpdId, mpId),
                MetaProfileErrorCode.dataChangeForbidden
        );
    }
}
