package com.example.kyros.api.controller;

import com.example.kyros.api.model.request.ClientUpdateRequestModel;
import com.example.kyros.api.model.response.ClientResponseModel;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseModel createClient(@Valid @RequestBody Client client){
        return clientService.createClient(client);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
    }

    @PutMapping("/{clientId}")
    public ClientResponseModel updateClient(@PathVariable Long clientId, @RequestBody ClientUpdateRequestModel clientUpdate){
        return clientService.updateClient(clientUpdate, clientId);
    }
}
