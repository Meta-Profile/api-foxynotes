package com.metaprofile.api.metaprofile.exceptions;

public class MetaProfileFieldNotInCategoryException extends RuntimeException {
    public MetaProfileFieldNotInCategoryException() {
        super("Field not in category");
    }

    public MetaProfileFieldNotInCategoryException(Long mpfId, Long mpcId) {
        super("Field mpf:" + mpfId + " is not in category i mpc:" + mpcId);
    }
}
