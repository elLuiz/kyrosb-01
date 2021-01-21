package com.example.kyros.domain.service.utils.input;

import com.example.kyros.domain.exception.invalidinput.CpfInvalidFormat;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class CpfValidator extends InputValidator implements Predicate<String> {
    private final static String REGEX = "[0-9]+";

    @Override
    public boolean test(String cpf) {
        if(!isNCharactersLong(cpf, 11))
            throw new CpfInvalidFormat("O cpf deve conter 11 caracteres.", 400);
        if(!inputMatches(cpf, REGEX))
            throw new CpfInvalidFormat("O cpf deve conter apenas caracteres númericos.", 400);

        return true;
    }
}
