package br.com.itau.postservice.exceptions;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.GENERIC_ERROR;

public class PostServiceException extends RuntimeException {

    private PostServiceExceptionType type;

    public PostServiceException(String message) {
        super(message);
        this.type = GENERIC_ERROR;
    }

    public PostServiceException(PostServiceExceptionType type) {
        super(type.getDefaultMessage());
        this.type = type;
    }

    public PostServiceException(String message, PostServiceExceptionType type) {
        this(message);
        this.type = type;
    }

    public PostServiceExceptionType getType() {
        return type;
    }
}
