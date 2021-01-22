package com.example.kyros.controller;

import com.example.kyros.KyrosApplication;
import com.example.kyros.api.model.request.ClientUpdateRequestModel;
import com.example.kyros.api.model.response.ClientResponseModel;
import com.example.kyros.domain.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = KyrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Setter
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    public String getRootURL(){
        return "http://localhost:" + port + "/api/v1/client";
    }

    @Test
    @Order(1)
    public void createNewClient_AndReturn201(){
        System.out.println("Created first " + LocalDateTime.now());
        Client luiz = createClientObject();
        ResponseEntity<ClientResponseModel> response = restTemplate.postForEntity(getRootURL(), luiz, ClientResponseModel.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Luiz", response.getBody().getName());
        assertEquals("luizin@gmail.com", response.getBody().getEmail());
        assertEquals("(93) 2312-9121", response.getBody().getPhone());
        assertEquals("212.984.212-00", response.getBody().getCpf());
    }

    @Test
    @Order(2)
    public void createNewClient_Returns400(){
        Client client = new Client();
        client.setName("John");
        client.setEmail("john@gmail232.com");
        client.setCpf("32244209122");

        ResponseEntity<ClientResponseModel> response = createPostRequest(client);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void updateExistingClient_Returns200(){
        System.out.println("Created 4th " + LocalDateTime.now());

        ResponseEntity<ClientResponseModel> response = createPutRequest();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("384.928.555-21", response.getBody().getCpf());
    }

    @Test
    @Order(4)
    public void deleteExistingClient_Returns204(){
        System.out.println("Created 5th" + LocalDateTime.now());

        ResponseEntity<Object> response = createDeleteRequest();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private ResponseEntity<ClientResponseModel> createPostRequest(Client client){
        return restTemplate.postForEntity(getRootURL(), client, ClientResponseModel.class);
    }

    private ResponseEntity<ClientResponseModel> createPutRequest(){
        ClientUpdateRequestModel clientUpdateRequestModel = new ClientUpdateRequestModel();
        clientUpdateRequestModel.setCpf("38492855521");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientUpdateRequestModel> httpEntity = new HttpEntity<>(clientUpdateRequestModel, httpHeaders);

        return restTemplate.exchange(getRootURL() + "/1",
                HttpMethod.PUT, httpEntity, ClientResponseModel.class);
    }

    private ResponseEntity<Object> createDeleteRequest(){
        return restTemplate.exchange(getRootURL() + "/1", HttpMethod.DELETE, null, Object.class);
    }


    private Client createClientObject(){
        Client client = new Client();
        client.setName("Luiz");
        client.setEmail("luizin@gmail.com");
        client.setPhone("9323129121");
        client.setCpf("21298421200");
        client.setBirthday(LocalDate.parse("1999-12-31"));

        return client;
    }
}
