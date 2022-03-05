package com.metaprofile.api.metaprofile.exceptions;

import com.metaprofile.api.metaprofile.errors.MetaProfileErrorMessage;
import com.metaprofile.api.metaprofile.errors.MetaProfileErrorCode;

import java.text.MessageFormat;

/**
 * Мета-профиль не найден
 */
public class MetaProfileNotFoundException extends MetaProfileException {

    /**
     * Поле не найдено
     */
    public static class FieldException extends MetaProfileException{
        public FieldException(Long mpfId){
            super(
                    MessageFormat.format(MetaProfileErrorMessage.notFoundField, mpfId),
                    MetaProfileErrorCode.notFoundField
            );
        }
    }


    /**
     * Мета данные не найдены
     */
    public static class DataException extends MetaProfileException{
        public DataException(Long mpdId){
            super(
                    MessageFormat.format(MetaProfileErrorMessage.notFoundData, mpdId),
                    MetaProfileErrorCode.notFoundData
            );
        }
    }


    /**
     * Мета данные не найдены
     */
    public static class CategoryException extends MetaProfileException{
        public CategoryException(Long mpcId){
            super(
                    MessageFormat.format(MetaProfileErrorMessage.notFoundCategory, mpcId),
                    MetaProfileErrorCode.notFoundCategory
            );
        }
    }

    public MetaProfileNotFoundException(Long mpId) {
        super(
                MessageFormat.format(MetaProfileErrorMessage.notFoundProfile, mpId),
                MetaProfileErrorCode.notFoundProfile
        );
    }
}
