package com.example.kyros.api.model.utils.formatter;

public class CpfFormatter implements ResponseFormatter {
    @Override
    public String formatInput(String cpf) {
        String regex = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
        String cpfFormatted = cpf.replaceFirst(regex, "$1.$2.$3-$4");
        return cpfFormatted;
    }
}
