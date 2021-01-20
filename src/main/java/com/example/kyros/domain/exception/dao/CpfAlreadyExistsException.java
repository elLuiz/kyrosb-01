package com.example.kyros.domain.exception.dao;

import com.example.kyros.domain.exception.ClientException;

public class CpfAlreadyExistsException extends ClientException {
    public CpfAlreadyExistsException(String message, int code) {
        super(message, code);
    }
}
