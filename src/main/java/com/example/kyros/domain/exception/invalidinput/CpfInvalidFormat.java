package com.example.kyros.domain.exception.invalidinput;

import com.example.kyros.domain.exception.ClientException;

public class CpfInvalidFormat extends ClientException {
    public CpfInvalidFormat(String message, int code) {
        super(message, code);
    }
}
