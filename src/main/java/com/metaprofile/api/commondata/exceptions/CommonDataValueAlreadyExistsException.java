package com.metaprofile.api.commondata.exceptions;

import com.metaprofile.api.commondata.CommonDataErrorMessage;

import java.text.MessageFormat;

public class CommonDataValueAlreadyExistsException extends CommonDataException {
    public CommonDataValueAlreadyExistsException(String title, Long mpfId) {
        super(
                MessageFormat.format(CommonDataErrorMessage.valueAlreadyExists, title, mpfId),
                5001
        );
    }
}
