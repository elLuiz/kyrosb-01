package com.example.kyros.domain.service.utils.input;

import org.springframework.stereotype.Service;

@Service
public class InputValidator {
    public boolean isFieldEmpty(String field){
        return field == null || field.length() == 0;
    }

    public boolean isValueEqual(String value, String newValue){
        return value.equals(newValue);
    }

    public boolean isNCharactersLong(String input, int length){
        return input.length() == length;
    }

    public boolean inputMatches(String input, String regex){
        return input.matches(regex);
    }
}