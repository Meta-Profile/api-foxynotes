package com.metaprofile.api.uploader.exceptions;

public class UploaderSessionForbiddenException extends UploaderSessionException{
    public UploaderSessionForbiddenException(){
        super("Uploader session owned by different user");
    }
}
