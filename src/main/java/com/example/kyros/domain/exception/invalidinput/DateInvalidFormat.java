package com.example.kyros.domain.exception.invalidinput;

import com.example.kyros.domain.exception.ClientException;

public class DateInvalidFormat extends ClientException {
    public DateInvalidFormat(String message, int code) {
        super(message, code);
    }
}
