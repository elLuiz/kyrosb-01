package com.example.kyros.domain.exception.invalidinput;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DateInvalidFormat extends JsonProcessingException {
    public DateInvalidFormat(String message) {
        super(message);
    }
}
