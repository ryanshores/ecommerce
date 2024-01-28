package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ApiResponse;
import com.ryanshores.ecommerce.enums.ErrorStatus;
import com.ryanshores.ecommerce.model.exception.AlreadyRegisteredException;
import com.ryanshores.ecommerce.model.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

public abstract class BaseRestController {

    private final Logger logger;

    public BaseRestController() {
        this.logger = LoggerFactory.getLogger(BaseRestController.class);
    }

    private ApiResponse<ErrorStatus> handleError(ErrorStatus status, HttpServletRequest request, Throwable ex) {
        logger.error(status.toString(), request.getRequestURL(), ex);
        return ApiResponse.error(status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<ErrorStatus> handleAccessDenied(HttpServletRequest request, Throwable ex) {
        return handleError(ErrorStatus.ACCESS_DENIED, request, ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorStatus> dataIntegrity(HttpServletRequest request, Exception ex) {
        return handleError(ErrorStatus.BAD_REQUEST, request, ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorStatus> resourceNotFoundException(HttpServletRequest request, Exception ex) {
        return handleError(ErrorStatus.NOT_FOUND, request, ex);
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<ErrorStatus> alreadyRegistered(HttpServletRequest request, Exception ex) {
        return handleError(ErrorStatus.ALREADY_REGISTERED, request, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorStatus> handleException(HttpServletRequest request, Exception ex) {
        return handleError(ErrorStatus.UNKNOWN_ERROR, request, ex);
    }
}
