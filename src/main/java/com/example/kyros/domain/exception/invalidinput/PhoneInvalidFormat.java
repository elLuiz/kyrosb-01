package com.example.kyros.domain.exception.invalidinput;

import com.example.kyros.domain.exception.ClientException;

public class PhoneInvalidFormat extends ClientException {
    public PhoneInvalidFormat(String message) {
        super(message);
    }
}
