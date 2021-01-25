package com.example.kyros.domain.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{
    private static final Long serialVersionUID = 1L;
    private int code;

    public ClientException(String message){
        super(message);
        this.code = 400;
    }

    public ClientException(String message, int code) {
        super(message);
        this.code = code;
    }

}
