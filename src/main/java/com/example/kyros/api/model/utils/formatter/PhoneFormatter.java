package com.example.kyros.api.model.utils.formatter;

public class PhoneFormatter implements ResponseFormatter {
    @Override
    public String formatInput(String phone) {
        String regex = "(\\d{2})(\\d+)(\\d{4})";
        String cpfFormatted = phone.replaceFirst(regex, "($1) $2-$3");
        return cpfFormatted;
    }
}
