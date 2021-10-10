package com.comoco.demoshop.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {
    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }
}
