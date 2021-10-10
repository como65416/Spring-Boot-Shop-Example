package com.comoco.demoshop.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;

@Data
public class HttpException extends ServletException {
    private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    private String message = "";

    public HttpException(String message) {
        this.message = message;
    }
}
