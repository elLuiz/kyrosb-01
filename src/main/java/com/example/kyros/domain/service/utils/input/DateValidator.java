package com.example.kyros.domain.service.utils.input;

import com.example.kyros.domain.exception.invalidinput.DateInvalidFormat;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

@Component
public class DateValidator extends InputValidator implements Predicate<String> {
    @Override
    public boolean test(String date) {
        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, dateTimeFormatter);
        }catch (DateTimeParseException dateTimeParseException){
            throw new DateInvalidFormat("O formato da data deve seguir o padrão yyyy-MM-dd.", 400);
        }

        return true;
    }
}
