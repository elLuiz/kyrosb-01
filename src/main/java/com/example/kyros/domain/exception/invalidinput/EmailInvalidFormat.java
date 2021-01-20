package com.example.kyros.domain.exception.invalidinput;

import com.example.kyros.domain.exception.ClientException;

public class EmailInvalidFormat extends ClientException {
    public EmailInvalidFormat(String message, int code) {
        super(message, code);
    }
}
