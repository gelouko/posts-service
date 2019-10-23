package br.com.itau.postservice.exceptions;

public class PostServiceException extends RuntimeException {

    private PostServiceExceptionType type;

    public PostServiceException(PostServiceExceptionType type) {
        super(type.getDefaultMessage());
        this.type = type;
    }

    public PostServiceExceptionType getType() {
        return type;
    }

    public PostServiceError toError() {
        return new PostServiceError(getMessage(), type);
    }
}
