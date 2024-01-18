package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ErrorDto;
import com.ryanshores.ecommerce.model.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private final Logger logger;

    public RestControllerExceptionHandler() {
        this.logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(HttpServletRequest request, Exception ex) {
        logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
        return new ErrorDto(request.getRequestURL().toString(), ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto resourceNotFoundException(HttpServletRequest request, NotFoundException ex) {
        logger.error(HttpStatus.NOT_FOUND.getReasonPhrase(), ex);
        return new ErrorDto(request.getRequestURL().toString(), ex);
    }
}
