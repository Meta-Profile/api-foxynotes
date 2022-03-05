package com.metaprofile.api.metaprofile.exceptions;

import com.metaprofile.api.metaprofile.errors.MetaProfileErrorMessage;
import com.metaprofile.api.metaprofile.errors.MetaProfileErrorCode;

import java.text.MessageFormat;

/**
 *  Сигнатура мета-поля не найдена в категории
 */
public class MetaProfileFieldNotInCategoryException extends MetaProfileException {
    public MetaProfileFieldNotInCategoryException(Long mpfId, Long mpcId) {
        super(
                MessageFormat.format(MetaProfileErrorMessage.fieldNotFoundInCategory, mpfId, mpcId),
                MetaProfileErrorCode.fieldNotInCategory
        );
    }
}
