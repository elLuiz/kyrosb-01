package com.example.kyros.domain.service.utils.input;

import com.example.kyros.domain.exception.invalidinput.PhoneInvalidFormat;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PhoneValidator extends InputValidator implements Predicate<String> {
    private final static String REGEX = "[0-9]+";

    @Override
    public boolean isNCharactersLong(String input, int length) {
        return input.length() >= 10;
    }

    @Override
    public boolean test(String phone) {
        if(!inputMatches(phone, REGEX))
            throw new PhoneInvalidFormat("O n° de telefone deve conter apenas caracteres númericos");
        if(!isNCharactersLong(phone, 10))
            throw new PhoneInvalidFormat("O n° de telefone deve conter no máximo 11 caracteres.");

        return true;
    }

}
