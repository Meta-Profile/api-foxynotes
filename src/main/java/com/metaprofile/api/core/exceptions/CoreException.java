package com.metaprofile.api.core.exceptions;

/**
 * Основное исключение движка
 */
public class CoreException extends RuntimeException{
    private Integer code = 400;

    public CoreException(String message){
        super(message);
    }

    public CoreException(String message, Integer code){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
