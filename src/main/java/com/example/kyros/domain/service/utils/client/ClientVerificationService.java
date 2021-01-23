package com.example.kyros.domain.service.utils.client;

import com.example.kyros.api.dto.request.ClientUpdateRequestModel;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.repository.ClientRepository;
import com.example.kyros.domain.service.utils.input.CpfValidator;
import com.example.kyros.domain.service.utils.input.EmailValidator;
import com.example.kyros.domain.service.utils.input.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class ClientVerificationService {
    @Autowired
    protected EmailValidator emailValidator;
    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected CpfValidator cpfValidator;
    @Autowired
    protected PhoneValidator phoneValidator;

    public boolean isClientSignedUp(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent())
            return true;
        return false;
    }

    public boolean isFieldEmpty(String field){
        return field == null || field.length() == 0;
    }

    public boolean isValueEqual(String value, String newValue){
        return value.equals(newValue);
    }

    public boolean isEmailSignedUp(String email){
        Optional<Client> client = clientRepository.findByEmail(email);
        if(client.isPresent())
            return true;
        return false;
    }

    public boolean isCPFSignedUp(String cpf){
        Optional<Client> client = clientRepository.findByCpf(cpf);
        if(client.isPresent())
            return true;
        return false;
    }

    public void verifyClientInput(Client client){};
    public void verifyClientInput(ClientUpdateRequestModel clientUpdateRequestModel){};
    protected abstract void verifyIfDataAlreadyExists();
    protected abstract void testUserInput();
}
