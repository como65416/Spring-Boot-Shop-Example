package com.xenby.demo.exception;

import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;

public class HttpException extends ServletException {
    private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    private String message = "";

    public HttpException(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
