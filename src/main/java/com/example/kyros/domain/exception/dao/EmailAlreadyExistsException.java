package com.example.kyros.domain.exception.dao;

import com.example.kyros.domain.exception.ClientException;

public class EmailAlreadyExistsException extends ClientException {
    public EmailAlreadyExistsException(String message, int code) {
        super(message, code);
    }
}
