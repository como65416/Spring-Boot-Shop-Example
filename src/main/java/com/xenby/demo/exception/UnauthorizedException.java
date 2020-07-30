package com.xenby.demo.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {
    private HttpStatus statusCode = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
