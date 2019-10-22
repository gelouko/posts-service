package br.com.itau.postservice.exceptions;

public enum PostServiceExceptionType {
    GENERIC_ERROR(1, "An unexpected error has occured."),
    JSON_PLACEHOLDER_RESPONSE_ERROR(2, "Error received from the JSON Placeholder API.");

    private Integer code;
    private String defaultMessage;

    PostServiceExceptionType(Integer code) {
        this.code = code;
    }

    PostServiceExceptionType(Integer code, String defaultMessage) {
        this(code);
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return "PST" + "-" + code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
