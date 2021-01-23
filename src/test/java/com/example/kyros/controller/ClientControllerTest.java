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
    public void post_NewClient_AndReturn201(){
        Client client = new Client();
        client.setName("Luiz");
        client.setEmail("luizin@gmail.com");
        client.setPhone("9323129121");
        client.setCpf("21298421200");
        client.setBirthday(LocalDate.parse("1999-12-31"));
        ResponseEntity<ClientResponseModel> response = restTemplate.postForEntity(getRootURL(), client, ClientResponseModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Luiz", response.getBody().getName());
        assertEquals("luizin@gmail.com", response.getBody().getEmail());
        assertEquals("(93) 2312-9121", response.getBody().getPhone());
        assertEquals("212.984.212-00", response.getBody().getCpf());
    }

    @Test
    @Order(2)
    public void post_NewClient_Returns400(){
        Client client = new Client();
        client.setName("John");
        client.setEmail("john@gmail232.com");
        client.setCpf("32244209122");
        ResponseEntity<ClientResponseModel> response = createPostRequest(client);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void post_ExistingCpfAndEmail_Returns400(){
        Client client = new Client();
        client.setName("Zico");
        client.setEmail("luizin@gmail.com");
        client.setPhone("9323129121");
        client.setCpf("21298421200");
        client.setBirthday(LocalDate.parse("1999-12-31"));
        ResponseEntity<ClientResponseModel> response = createPostRequest(client);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(4)
    public void update_ExistingClient_Returns200(){
        ClientUpdateRequestModel clientUpdateRequestModel = new ClientUpdateRequestModel();
        clientUpdateRequestModel.setCpf("38492855521");
        ResponseEntity<ClientResponseModel> response = createPutRequest(clientUpdateRequestModel, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("384.928.555-21", response.getBody().getCpf());
    }

    @Test
    @Order(5)
    public void update_Non_ExistingClient_Returns404(){
        ClientUpdateRequestModel clientUpdateRequestModel = new ClientUpdateRequestModel();
        clientUpdateRequestModel.setCpf("38492855521");
        ResponseEntity<ClientResponseModel> response = createPutRequest(clientUpdateRequestModel, 2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Order(6)
    public void deleteExistingClient_Returns204(){
        ResponseEntity<Object> response = createDeleteRequest(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @Order(7)
    public void detele_Non_ExistingCLient_Returns404(){
        ResponseEntity<Object> response = createDeleteRequest(10);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private ResponseEntity<ClientResponseModel> createPostRequest(Client client){
        return restTemplate.postForEntity(getRootURL(), client, ClientResponseModel.class);
    }

    private ResponseEntity<ClientResponseModel> createPutRequest(ClientUpdateRequestModel clientUpdateRequestModel, long id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientUpdateRequestModel> httpEntity = new HttpEntity<>(clientUpdateRequestModel, httpHeaders);

        return restTemplate.exchange(getRootURL() + "/" + id,
                HttpMethod.PUT, httpEntity, ClientResponseModel.class);
    }

    private ResponseEntity<Object> createDeleteRequest(long id){
        return restTemplate.exchange(getRootURL() + "/" + id, HttpMethod.DELETE, null, Object.class);
    }
}
