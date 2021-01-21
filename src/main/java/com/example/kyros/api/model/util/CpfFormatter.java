package com.example.kyros.api.model.util;

public class CpfFormatter extends ResponseFormatter{
    @Override
    public String formatInput(String cpf) {
        String regex = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
        String cpfFormatted = cpf.replaceFirst(regex, "$1.$2.$3-$4");
        return cpfFormatted;
    }
}
