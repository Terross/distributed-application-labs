package ru.leti.distributed.application.labs.error;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandleAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
