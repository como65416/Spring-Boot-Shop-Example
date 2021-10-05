package com.comoco.demoshop.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpException {
    private HttpStatus statusCode = HttpStatus.FORBIDDEN;

    public ForbiddenException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
