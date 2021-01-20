package com.example.kyros.domain.service.utils;

import org.springframework.stereotype.Service;

@Service
public class InputValidator {

    public boolean isNCharactersLong(String input, int length){
        return input.length() == length;
    }

    public boolean inputMatches(String input, String regex){
        return input.matches(regex);
    }
}
