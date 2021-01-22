package com.example.kyros.domain.service.utils.input;

import com.example.kyros.domain.exception.invalidinput.EmailInvalidFormat;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class EmailValidator extends InputValidator implements Predicate<String>{
    private static final String REGEX = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

    @Override
    public boolean test(String inputTest) {
        if (inputMatches(inputTest, REGEX))
            return true;

        throw new EmailInvalidFormat("Formato de email inválido", 400);
    }

}
