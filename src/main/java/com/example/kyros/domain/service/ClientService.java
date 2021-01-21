package com.example.kyros.domain.service;

import com.example.kyros.api.model.util.CpfFormatter;
import com.example.kyros.api.model.util.PhoneFormatter;
import com.example.kyros.api.model.util.ResponseFormatter;
import com.example.kyros.domain.exception.dao.ClientNotFoundException;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.repository.ClientRepository;
import com.example.kyros.domain.service.utils.client.ClientInsertionVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientInsertionVerificationService clientInsertionVerificationService;


    public Client createClient(Client client){
        clientInsertionVerificationService.verifyClientInput(client);
        Client clientResponse = clientRepository.save(client);
        createClientResponse(clientResponse);
        return clientResponse;
    }

    public void deleteClient(Long id) {
        if(clientInsertionVerificationService.isClientSignedUp(id))
            clientRepository.deleteById(id);
        else
            throw new ClientNotFoundException("Cliente não encontrado", 404);
    }

    private void createClientResponse(Client client){
        ResponseFormatter cpfFormatter = new CpfFormatter();
        ResponseFormatter phoneFormatter = new PhoneFormatter();
        client.setCpf(cpfFormatter.formatInput(client.getCpf()));
        client.setPhone(phoneFormatter.formatInput(client.getPhone()));
    }
}
