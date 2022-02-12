package com.metaprofile.api.payloads.request;

import com.metaprofile.api.model.enums.FileStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class FileStatusRequest {
    @Enumerated(EnumType.ORDINAL)
    private FileStatus status;

    public FileStatusRequest(){
        this.status = FileStatus.PRIVATE;
    }

    public FileStatusRequest(FileStatus status) {
        this.status = status;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }
}
