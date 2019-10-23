package br.com.itau.postservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(PostServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PostServiceError handlePostServiceException(PostServiceException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new PostServiceError(ex.getMessage(), ex.getType());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public PostServiceError handleGenericException(Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new PostServiceError(ex.getMessage());
    }

}
