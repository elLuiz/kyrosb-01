package com.example.kyros.domain.service.utils.client;

import com.example.kyros.api.dto.request.ClientUpdateRequestModel;
import com.example.kyros.domain.exception.ClientException;
import com.example.kyros.domain.exception.dao.CpfAlreadyExistsException;
import com.example.kyros.domain.exception.dao.EmailAlreadyExistsException;
import com.example.kyros.domain.model.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class ClientUpdateService extends ClientVerificationService{
    private ClientUpdateRequestModel clientUpdateRequestModel;

    @Override
    public void verifyClientInput(ClientUpdateRequestModel client) {
        setClientUpdateRequestModel(client);
        testUserInput();
        verifyIfDataAlreadyExists();
    }

    public Client updateClient(ClientUpdateRequestModel clientRequest, Long clientId){
        Client client = clientRepository.findById(clientId).get();
        isValueDifferent(clientRequest, client);
        updateExistingClientObject(client);

        return client;
    }

    @Override
    protected void testUserInput() {
        String cpf = clientUpdateRequestModel.getCpf();
        String phone = clientUpdateRequestModel.getPhone();

        if(!inputValidator.isFieldEmpty(cpf))
            cpfValidator.test(cpf);
        if(!inputValidator.isFieldEmpty(phone))
            phoneValidator.test(phone);
    }

    @Override
    protected void verifyIfDataAlreadyExists() {
        if(!inputValidator.isFieldEmpty(clientUpdateRequestModel.getEmail())){
            if(isEmailSignedUp(clientUpdateRequestModel.getEmail()))
                throw new EmailAlreadyExistsException("O email já está em uso.", 400);
        }

        if(!inputValidator.isFieldEmpty(clientUpdateRequestModel.getCpf())){
            if(isCPFSignedUp(clientUpdateRequestModel.getCpf()))
                throw new CpfAlreadyExistsException("O cpf já está cadastrado", 400);
        }
    }

    private void updateExistingClientObject(Client client) {
        if(!inputValidator.isFieldEmpty(clientUpdateRequestModel.getEmail()))
            client.setEmail(clientUpdateRequestModel.getEmail());

        if(!inputValidator.isFieldEmpty(clientUpdateRequestModel.getCpf()))
            client.setCpf(clientUpdateRequestModel.getCpf());

        if(!inputValidator.isFieldEmpty(clientUpdateRequestModel.getPhone()))
            client.setPhone(clientUpdateRequestModel.getPhone());
    }

    private void isValueDifferent(ClientUpdateRequestModel clientRequest, Client client) {
        if(inputValidator.isValueEqual(client.getEmail(), clientRequest.getEmail()))
            throw new ClientException("O email deve ser diferente do registrado.", 400);

        if(inputValidator.isValueEqual(client.getCpf(), clientRequest.getCpf()))
            throw new ClientException("O nº cpf deve ser diferente do registrado no Banco de dados", 400);

        if(inputValidator.isValueEqual(client.getPhone(), clientRequest.getPhone()))
            throw new ClientException("O nº de telefone deve ser diferente do registrado", 400);
    }
}
