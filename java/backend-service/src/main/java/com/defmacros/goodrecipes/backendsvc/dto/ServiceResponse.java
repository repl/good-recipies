package com.defmacros.goodrecipes.backendsvc.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceResponse<T> {
    private T data;
    private boolean success;
    private List<String> messages = new ArrayList();
    private List<ServiceError> errors = new ArrayList();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<ServiceError> getErrors() {
        return this.errors;
    }

    public void setErrors(final List<ServiceError> errors) {
        this.errors = errors;
    }

    public static ServiceResponse ok() {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(true);
        return response;
    }

    public static <T> ServiceResponse ok(T data) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> ServiceResponse ok(String message, T data) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(true);
        response.setMessages(Arrays.asList(message));
        response.setData(data);
        return response;
    }

    public static ServiceResponse error(String errorCode, String errorMessage) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(false);
        response.setErrors(Arrays.asList(new ServiceError(errorCode, errorMessage)));
        return response;
    }

    public static ServiceResponse error(String message, String errorCode, String errorMessage) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(false);
        response.setMessages(Arrays.asList(message));
        response.setErrors(Arrays.asList(new ServiceError(errorCode, errorMessage)));
        return response;
    }

    public static ServiceResponse error(ServiceError error) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(false);
        response.setErrors(Arrays.asList(error));
        return response;
    }

    public static ServiceResponse error(List<ServiceError> errors) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(false);
        response.setErrors(errors);
        return response;
    }

    public static ServiceResponse error(String message, List<ServiceError> errors) {
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(false);
        response.setMessages(Arrays.asList(message));
        response.setErrors(errors);
        return response;
    }
}
