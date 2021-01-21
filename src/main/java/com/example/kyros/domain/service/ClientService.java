package com.example.kyros.domain.service;

import com.example.kyros.api.model.request.ClientUpdateRequestModel;
import com.example.kyros.api.model.response.ClientResponseModel;
import com.example.kyros.api.model.utils.converter.ModelMapperConverter;
import com.example.kyros.domain.exception.dao.ClientNotFoundException;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.repository.ClientRepository;
import com.example.kyros.domain.service.utils.client.ClientInsertionService;
import com.example.kyros.domain.service.utils.client.ClientUpdateService;
import com.example.kyros.domain.service.utils.client.ClientVerificationService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter
@Getter
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientInsertionService clientInsertionService;
    @Autowired
    private ClientUpdateService clientUpdateService;
    @Autowired
    private ModelMapperConverter modelMapperConverter;

    public ClientResponseModel createClient(Client client){
        clientInsertionService.verifyClientInput(client);
        Client clientResponse = clientRepository.save(client);
        return modelMapperConverter.convertToClientResponseModel(clientResponse);
    }

    public ClientResponseModel updateClient(ClientUpdateRequestModel client, Long clientId){
        if(clientUpdateService.isClientSignedUp(clientId)){
            clientUpdateService.verifyClientInput(client);
            Client clientUpdate = clientUpdateService.updateClient(client, clientId);
            clientUpdate = clientRepository.save(clientUpdate);
            return modelMapperConverter.convertToClientResponseModel(clientUpdate);
        }else
            throw new ClientNotFoundException("Cliente não encontrado", 404);
    }

    public void deleteClient(Long id) {
        if(clientUpdateService.isClientSignedUp(id))
            clientRepository.deleteById(id);
        else
            throw new ClientNotFoundException("Cliente não encontrado", 404);
    }
}
