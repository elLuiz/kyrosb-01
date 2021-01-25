package com.example.kyros.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ClientResponseModel{
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private LocalDate birthday;
}
