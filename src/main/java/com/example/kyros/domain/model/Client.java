package com.example.kyros.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "{name.not.blank}")
    @Size(max = 255, message = "{name.size}")
    private String name;
    @NotBlank(message = "{cpf.not.blank}")
    @Size(max = 11, message = "{cpf.size}")
    private String cpf;
    @NotBlank(message = "{email.not.blank}")
    @Size(max = 255, message = "{email.size}")
    private String email;
    @NotBlank(message = "{phone.not.blank}")
    @Size(max = 11, message = "{phone.size}")
    private String phone;
    @NotNull(message = "{birthday.not.null}")
    private LocalDate birthday;
}
