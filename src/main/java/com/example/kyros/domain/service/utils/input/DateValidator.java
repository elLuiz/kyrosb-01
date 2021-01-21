package com.example.kyros.domain.service.utils.input;

import com.example.kyros.domain.exception.invalidinput.DateInvalidFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class DateValidator extends InputValidator{
    @Override
    public boolean test(Object inputTest) {
        try{
            LocalDate.parse((String) inputTest);
        }catch (DateTimeParseException dateTimeParseException){
            throw new DateInvalidFormat("O formato da data deve seguir o padrão yyyy-MM-dd.", 400);
        }

        return true;
    }
}
