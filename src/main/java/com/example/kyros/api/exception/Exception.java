package com.example.kyros.api.exception;

import com.example.kyros.api.model.ExceptionModel;
import com.example.kyros.domain.exception.ClientException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException clientException, WebRequest webRequest){
        HttpStatus status = HttpStatus.resolve(clientException.getCode());
        ExceptionModel exceptionModel = new ExceptionModel(status.value(), clientException.getMessage(), OffsetDateTime.now());
        return handleExceptionInternal(clientException, exceptionModel, new HttpHeaders(), status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionModel exceptionModel = new ExceptionModel(status.value(),
                "Erro na requisição.",
                OffsetDateTime.now(),
                getFieldsError(ex));
        return super.handleExceptionInternal(ex, exceptionModel,headers, status, request);
    }

    private ArrayList<ExceptionModel.Fields> getFieldsError(MethodArgumentNotValidException methodArgumentNotValidException){
        ArrayList<ExceptionModel.Fields> fields = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors()
                .stream()
                .forEach(error -> fields.add(new ExceptionModel.Fields(((FieldError) error).getField(), error.getDefaultMessage())));

        return fields;
    }
}
