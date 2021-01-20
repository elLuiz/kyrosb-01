package com.example.kyros.api.exception;

import com.example.kyros.api.model.ExceptionModel;
import com.example.kyros.domain.exception.ClientException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException clientException, WebRequest webRequest){
        HttpStatus status = HttpStatus.resolve(clientException.getCode());
        ExceptionModel exceptionModel = new ExceptionModel(status.value(), clientException.getMessage(), OffsetDateTime.now());
        return handleExceptionInternal(clientException, exceptionModel, new HttpHeaders(), status, webRequest);
    }
}
