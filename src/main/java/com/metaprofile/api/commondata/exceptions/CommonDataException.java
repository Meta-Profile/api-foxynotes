package com.metaprofile.api.commondata.exceptions;

public class CommonDataException extends RuntimeException{
    private Integer code;
    public CommonDataException(String message, Integer code){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
