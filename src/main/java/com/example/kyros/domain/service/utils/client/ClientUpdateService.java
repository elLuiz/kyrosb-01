package com.example.kyros.domain.service.utils.client;

import com.example.kyros.api.model.request.ClientUpdateRequestModel;
import com.example.kyros.domain.exception.ClientException;
import com.example.kyros.domain.exception.dao.CpfAlreadyExistsException;
import com.example.kyros.domain.exception.dao.EmailAlreadyExistsException;
import com.example.kyros.domain.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClientUpdateService extends ClientVerificationService{
    @Override
    public void verifyClientInput(ClientUpdateRequestModel client) {
        String email = client.getEmail();
        String cpf = client.getCpf();
        String phone = client.getPhone();

        if(!isFieldEmpty(email))
            emailValidator.test(email);
        if(!isFieldEmpty(cpf))
            cpfValidator.test(cpf);
        if(!isFieldEmpty(phone))
            phoneValidator.test(phone);
    }

    public Client updateClient(ClientUpdateRequestModel clientRequest, Long clientId){
        Client client = clientRepository.findById(clientId).get();
        isValueDifferent(clientRequest, client);
        updateClientObject(clientRequest, client);

        return client;
    }

    private void updateClientObject(ClientUpdateRequestModel clientRequest, Client client) {
        if(!isFieldEmpty(clientRequest.getEmail())){
            if(isEmailSignedUp(clientRequest.getEmail()))
                throw new EmailAlreadyExistsException("O email já está em uso.", 400);
            client.setEmail(clientRequest.getEmail());
        }
        if(!isFieldEmpty(client.getCpf())){
            if(isCPFSignedUp(clientRequest.getCpf()))
                throw new CpfAlreadyExistsException("O cpf já está cadastrado", 400);
            client.setCpf(clientRequest.getCpf());
        }
        if(!isFieldEmpty(clientRequest.getPhone()))
            client.setPhone(clientRequest.getPhone());
    }

    private void isValueDifferent(ClientUpdateRequestModel clientRequest, Client client) {
        if(isValueEqual(client.getEmail(), clientRequest.getEmail()))
            throw new ClientException("O email deve ser diferente do registrado.", 400);
        if(isValueEqual(client.getCpf(), clientRequest.getCpf()))
            throw new ClientException("O nº cpf deve ser diferente do registrado no Banco de dados", 400);
        if(isValueEqual(client.getPhone(), clientRequest.getPhone()))
            throw new ClientException("O nº de telefone deve ser diferente do registrado", 400);
    }
}
