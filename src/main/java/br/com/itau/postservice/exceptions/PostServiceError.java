package br.com.itau.postservice.exceptions;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.GENERIC_ERROR;

public class PostServiceError {

    private String message;
    private String code;
    private String type;

    public PostServiceError(String message, PostServiceExceptionType type) {
        this.message = message;
        this.code = type.getCode();
        this.type = type.name();
    }

    public PostServiceError(String message) {
        this.message = message;
        this.type = GENERIC_ERROR.name();
        this.code = GENERIC_ERROR.getCode();
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
