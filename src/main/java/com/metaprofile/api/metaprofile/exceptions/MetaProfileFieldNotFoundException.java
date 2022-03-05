package com.metaprofile.api.metaprofile.exceptions;

public class MetaProfileFieldNotFoundException extends RuntimeException{
    public MetaProfileFieldNotFoundException() {
        super("Meta profile field not found");
    }
}
