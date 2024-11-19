package com.example.study.global.apiPayload.handler;

import com.example.study.global.apiPayload.code.BaseErrorCode;
import com.example.study.global.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
