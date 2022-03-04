package com.metaprofile.api.metaprofile.exceptions;

public class MetaProfileCategoryNotFoundException extends RuntimeException{
    public MetaProfileCategoryNotFoundException() {
        super("Meta profile category not found");
    }
}
