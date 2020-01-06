package com.defmacros.goodrecipes.websvc.exception;

public class ServiceException extends Exception {
    private String errorCode;
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
}