package com.example.kyros.domain.service;

import com.example.kyros.api.dto.request.ClientUpdateRequestModel;
import com.example.kyros.api.dto.response.ClientResponseModel;
import com.example.kyros.api.dto.utils.converter.ModelMapperConverter;
import com.example.kyros.domain.exception.dao.ClientNotFoundException;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.repository.ClientRepository;
import com.example.kyros.domain.service.utils.client.ClientInsertionService;
import com.example.kyros.domain.service.utils.client.ClientUpdateService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

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

    public ClientResponseModel listClient(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado", 404));
        return modelMapperConverter.convertToClientResponseModel(client);
    }

    public ClientResponseModel createClient(Client client) {
        clientInsertionService.verifyClientInput(client);
        return getClientResponseModel(client);
    }

    public ClientResponseModel updateClient(ClientUpdateRequestModel client, Long clientId){
        if(clientUpdateService.isClientSignedUp(clientId)){
            clientUpdateService.verifyClientInput(client);
            Client clientUpdate = clientUpdateService.updateClient(client, clientId);
            return getClientResponseModel(clientUpdate);
        }else
            throw new ClientNotFoundException("Cliente não encontrado", 404);
    }

    public void deleteClient(Long id) {
        if(clientUpdateService.isClientSignedUp(id))
            clientRepository.deleteById(id);
        else
            throw new ClientNotFoundException("Cliente não encontrado", 404);
    }

    private ClientResponseModel getClientResponseModel(Client client) {
        Client clientResponse = clientRepository.save(client);
        return modelMapperConverter.convertToClientResponseModel(clientResponse);
    }
}
