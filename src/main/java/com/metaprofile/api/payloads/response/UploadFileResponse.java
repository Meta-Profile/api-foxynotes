package com.metaprofile.api.payloads.response;

import com.metaprofile.api.model.File;

public class UploadFileResponse {
    private final Long fileId;
    private final String name;
    private final String url;
    private final String type;
    private final long size;

    public UploadFileResponse(File file) {
        this.fileId = file.getId();
        this.name = file.getName();
        this.url = file.getURL();
        this.type = file.getType();
        this.size = file.getSize();
    }

    public Long getFileId() {
        return fileId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public long getSize() {
        return size;
    }
}
