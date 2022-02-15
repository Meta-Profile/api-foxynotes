package com.metaprofile.api.uploader.exceptions;

public class UploaderSessionNotFoundException extends UploaderSessionException{
    public UploaderSessionNotFoundException(){
        super("Uploader session not found");
    }
}
