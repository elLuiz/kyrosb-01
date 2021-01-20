package com.example.kyros.domain.exception.dao;

import com.example.kyros.domain.exception.ClientException;

public class ClientNotFoundException extends ClientException {
    public ClientNotFoundException(String message, int code) {
        super(message, code);
    }
}
