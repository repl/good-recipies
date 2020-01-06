package com.defmacros.goodrecipes.backendsvc.utils;

public class ErrorConstants {
    /* Generic */
    public static final String ERROR_CODE_GENERIC_ERROR = "E000";

    public static final String ERROR_CODE_SERVER_INTERVAL_ERROR = "E001";
    public static final String ERROR_MSG_SERVER_INTERVAL_ERROR = "An unknown error occurred. Error reference code: {1}";

    public static final String ERROR_CODE_REQUIRED_ATTRIBUTE = "E002";
    public static final String ERROR_MSG_REQUIRED_ATTRIBUTE = "{1} is a required attribute";

    public static final String ERROR_CODE_REQUIRED_REQUEST_PARAMETER = "E003";
    public static final String ERROR_MSG_REQUIRED_REQUEST_PARAMETER = "{1} is a required request parameter";

    public static final String ERROR_CODE_INPUT_WITH_INVALID_FORMAT = "E004";
    public static final String ERROR_MSG_INPUT_WITH_INVALID_FORMAT = "{1} doesn't have a valid format.";
}
