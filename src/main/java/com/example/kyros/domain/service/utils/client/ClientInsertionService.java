package com.example.kyros.domain.service.utils.client;

import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.service.utils.input.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClientInsertionService extends ClientVerificationService{
    @Autowired
    private DateValidator dateValidator;

    @Override
    public void verifyClientInput(Client client) {
        String email = client.getEmail();
        String cpf = client.getCpf();
        String phone = client.getPhone();
        String birthday = client.getBirthday().toString();

        emailValidator.test(email);
        phoneValidator.test(phone);
        cpfValidator.test(cpf);
        dateValidator.test(birthday);
    }
}
