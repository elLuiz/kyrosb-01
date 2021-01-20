package com.example.kyros.api.controller;

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
    public Client createClient(@Valid @RequestBody Client client){
        return clientService.createClient(client);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
    }
}
