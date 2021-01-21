package com.example.kyros.api.model.util;

public class PhoneFormatter extends ResponseFormatter{
    @Override
    public String formatInput(String phone) {
        String regex = "(\\d{2})(\\d+)(\\d{4})";
        String cpfFormatted = phone.replaceFirst(regex, "($1) $2-$3");
        return cpfFormatted;
    }
}
