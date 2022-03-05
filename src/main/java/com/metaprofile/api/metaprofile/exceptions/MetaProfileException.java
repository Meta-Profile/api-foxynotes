package com.metaprofile.api.metaprofile.exceptions;

import com.metaprofile.api.metaprofile.errors.MetaProfileErrorCode;

public class MetaProfileException extends RuntimeException{
    private Integer code = MetaProfileErrorCode.badRequest;

    public MetaProfileException(){
        super("Meta profile undefined exception");
    }

    public MetaProfileException(String message){
        super(message);
    }

    public MetaProfileException(String message, Integer code){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
