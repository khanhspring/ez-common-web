package com.codelaez.ezcommonweb.web;

import com.codelaez.ezcommon.exception.*;
import com.codelaez.ezcommonweb.tracing.CurrentTracingContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author khanhspring
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(UnauthorizedException e) {
        return ErrorResponse.builder()
                .code(e.getError().getCode())
                .type(e.getError().getType())
                .subCode(e.getError().getSubCode())
                .message(e.getError().getMessage())
                .traceId(CurrentTracingContextHolder.getContext().getTraceId())
                .build();
    }
    @ExceptionHandler(value = {ForbiddenException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(ForbiddenException e) {
        return ErrorResponse.builder()
                .code(e.getError().getCode())
                .type(e.getError().getType())
                .subCode(e.getError().getSubCode())
                .message(e.getError().getMessage())
                .traceId(CurrentTracingContextHolder.getContext().getTraceId())
                .build();
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handle(ResourceNotFoundException e) {
        return ErrorResponse.builder()
                .code(e.getError().getCode())
                .type(e.getError().getType())
                .subCode(e.getError().getSubCode())
                .message(e.getError().getMessage())
                .traceId(CurrentTracingContextHolder.getContext().getTraceId())
                .build();
    }

    @ExceptionHandler(value = {ApplicationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(ApplicationException e) {
        return ErrorResponse.builder()
                .code(e.getError().getCode())
                .type(e.getError().getType())
                .subCode(e.getError().getSubCode())
                .message(e.getError().getMessage())
                .traceId(CurrentTracingContextHolder.getContext().getTraceId())
                .build();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception e) {
        log.error("Unknown error", e);
        return ErrorResponse.builder()
                .code(CommonError.SYSTEM_BUSY.getCode())
                .type(CommonError.SYSTEM_BUSY.getType())
                .subCode(CommonError.SYSTEM_BUSY.getSubCode())
                .message(CommonError.SYSTEM_BUSY.getMessage())
                .traceId(CurrentTracingContextHolder.getContext().getTraceId())
                .build();
    }
}
