//package com.ryanshores.ecommerce.controller;
//
//import com.ryanshores.ecommerce.dto.ApiResponse;
//import com.ryanshores.ecommerce.enums.ErrorStatus;
//import com.ryanshores.ecommerce.model.exception.AlreadyRegisteredException;
//import com.ryanshores.ecommerce.model.exception.NotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.web.header.writers.frameoptions.AllowFromStrategy;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.nio.file.AccessDeniedException;
//
//@RestControllerAdvice
//public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
//
//    private final Logger logger;
//
//    public RestControllerExceptionHandler() {
//        this.logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);
//    }
//
//    private ApiResponse<ErrorStatus> handleError(ErrorStatus status, HttpServletRequest request, Throwable ex) {
//        logger.error(status.toString(), request.getRequestURL(), ex);
//        return ApiResponse.error(status);
//    }
//
//    @ExceptionHandler(value = AccessDeniedException.class)
//    public ApiResponse<ErrorStatus> handleAccessDenied(HttpServletRequest request, AccessDeniedException ex) {
//        return handleError(ErrorStatus.ACCESS_DENIED, request, ex);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiResponse<ErrorStatus> dataIntegrity(HttpServletRequest request, Exception ex) {
//        return handleError(ErrorStatus.BAD_REQUEST, request, ex);
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiResponse<ErrorStatus> resourceNotFoundException(HttpServletRequest request, Exception ex) {
//        return handleError(ErrorStatus.NOT_FOUND, request, ex);
//    }
//
//    @ExceptionHandler(AlreadyRegisteredException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ApiResponse<ErrorStatus> alreadyRegistered(HttpServletRequest request, Exception ex) {
//        return handleError(ErrorStatus.ALREADY_REGISTERED, request, ex);
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiResponse<ErrorStatus> handleException(HttpServletRequest request, Exception ex) {
//        return handleError(ErrorStatus.UNKNOWN_ERROR, request, ex);
//    }
//}
