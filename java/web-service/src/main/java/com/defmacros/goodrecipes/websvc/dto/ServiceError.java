package com.defmacros.goodrecipes.websvc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceError {
    private String code;
    private String message;

    public ServiceError() {
    }

    public ServiceError(String errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }
}