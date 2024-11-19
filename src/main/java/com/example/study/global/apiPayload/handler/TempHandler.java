package com.example.study.global.apiPayload.handler;

import com.example.study.global.apiPayload.code.BaseErrorCode;
import com.example.study.global.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
