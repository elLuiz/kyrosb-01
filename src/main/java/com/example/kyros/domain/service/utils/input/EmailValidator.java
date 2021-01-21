package com.example.kyros.domain.service.utils.input;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class EmailValidator extends InputValidator implements Predicate<String>{
    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    @Override
    public boolean test(String inputTest) {
        if(inputMatches(inputTest, REGEX))
            return true;
        return false;
    }

}
