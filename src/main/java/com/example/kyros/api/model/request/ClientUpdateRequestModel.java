package com.example.kyros.api.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ClientUpdateRequestModel {
    private String cpf;
    private String email;
    private String phone;
}
