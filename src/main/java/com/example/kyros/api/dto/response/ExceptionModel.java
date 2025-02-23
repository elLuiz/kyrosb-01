package com.example.kyros.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionModel {
    private int status;
    private String message;
    private OffsetDateTime dateException;
    private ArrayList<Fields> fields;

    public ExceptionModel(int status, String message, OffsetDateTime dateException) {
        this.status = status;
        this.message = message;
        this.dateException = dateException;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Fields{
        private String field;
        private String message;
    }
}
