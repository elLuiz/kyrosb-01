package com.example.kyros.domain.service.utils.client;

import com.example.kyros.domain.exception.dao.CpfAlreadyExistsException;
import com.example.kyros.domain.exception.dao.EmailAlreadyExistsException;
import com.example.kyros.domain.model.Client;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class ClientInsertionService extends ClientVerificationService{
    private Client client;

    @Override
    public void verifyClientInput(Client client) {
        setClient(client);
        testUserInput();
        verifyIfDataAlreadyExists();
    }

    protected void testUserInput(){
        String email = client.getEmail();
        String cpf = client.getCpf();
        String phone = client.getPhone();

        emailValidator.test(email);
        phoneValidator.test(phone);
        cpfValidator.test(cpf);
    }

    protected void verifyIfDataAlreadyExists(){
        if(isEmailSignedUp(client.getEmail()))
            throw new EmailAlreadyExistsException("O email já está em uso.", 400);
        if(isCPFSignedUp(client.getCpf()))
            throw new CpfAlreadyExistsException("O cpf já está cadastrado", 400);
    }
}
