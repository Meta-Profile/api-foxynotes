package com.metaprofile.api.metaprofile.exceptions;

public class MetaProfileNotFoundException extends RuntimeException{
    public MetaProfileNotFoundException() {
        super("Meta profile not found");
    }
}
