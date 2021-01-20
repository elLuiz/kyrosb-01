package com.example.kyros.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionModel {
    private int status;
    private String message;
    private OffsetDateTime dateException;
}
