package com.metaprofile.api.metaprofile.exceptions;

public class MetaProfileFieldAlreadyExistsException extends RuntimeException {
    public MetaProfileFieldAlreadyExistsException() {
        super("Meta profile field has already exists");
    }

    public MetaProfileFieldAlreadyExistsException(Long mpfId, Long mpId) {
        super("Field mpf:" + mpfId + " has already exists in mp:" + mpId);
    }
}
