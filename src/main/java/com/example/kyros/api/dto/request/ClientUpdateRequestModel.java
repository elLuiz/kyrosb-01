package com.example.kyros.api.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@EqualsAndHashCode
public class ClientUpdateRequestModel {
    private String cpf;
    @Email(message = "{email.invalid.format}")
    private String email;
    private String phone;
}
