package com.example.kyros.domain.service;

import com.example.kyros.domain.exception.dao.ClientNotFoundException;
import com.example.kyros.domain.exception.dao.CpfAlreadyExistsException;
import com.example.kyros.domain.exception.dao.EmailAlreadyExistsException;
import com.example.kyros.domain.exception.invalidinput.*;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.repository.ClientRepository;
import com.example.kyros.domain.service.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InputValidator inputValidator;

    public Client createClient(Client client){
        verifyClientInput(client);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        if(isClientSignedUp(id))
            clientRepository.deleteById(id);
        else
            throw new ClientNotFoundException("Cliente não encontrado", 404);
    }


    private void verifyClientInput(Client client){
        String email = client.getEmail();
        String cpf = client.getCpf();
        String phone = client.getPhone();

        if(isEmailSignedUp(email))
            throw new EmailAlreadyExistsException("O email informado já está cadastrado.", 400);

        if(isCPFSignedUp(cpf))
            throw new CpfAlreadyExistsException("O cpf informado já está cadastrado", 400);

        if(!inputValidator.inputMatches(email,
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            throw new EmailInvalidFormat("O formato de email está errado", 400);

        if(!inputValidator.isNCharactersLong(cpf, 11))
            throw new CpfInvalidFormat("O cpf deve conter 11 caracteres.", 400);

        if(!inputValidator.inputMatches(cpf, "[0-9]+"))
            throw new CpfInvalidFormat("O cpf deve conter apenas caracteres númericos", 400);

        if(!inputValidator.inputMatches(phone, "[0-9]+"))
            throw new PhoneInvalidFormat("O n° telefone deve conter apenas dados númericos", 400);

        if(!inputValidator.isNCharactersLong(phone, 11))
            throw new PhoneInvalidFormat("O telefone deve conter 11 caracteres númericos.", 400);
    }

    private boolean isClientSignedUp(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent())
            return true;
        return false;
    }

    private boolean isEmailSignedUp(String email){
        Optional<Client> client = clientRepository.findByEmail(email);
        if(client.isPresent())
            return true;
        return false;
    }

    private boolean isCPFSignedUp(String cpf){
        Optional<Client> client = clientRepository.findByCpf(cpf);
        if(client.isPresent())
            return true;
        return false;
    }
}
