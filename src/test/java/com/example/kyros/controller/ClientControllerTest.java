package com.example.kyros.controller;

import com.example.kyros.KyrosApplication;
import com.example.kyros.api.controller.ClientController;
import com.example.kyros.api.model.request.ClientUpdateRequestModel;
import com.example.kyros.api.model.response.ClientResponseModel;
import com.example.kyros.api.model.utils.converter.ModelMapperConverter;
import com.example.kyros.domain.model.Client;
import com.example.kyros.domain.service.ClientService;
import com.example.kyros.domain.service.utils.client.ClientUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KyrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Setter
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
    public void contextLoads(){

    }

    @Test
    @Order(2)
    public void createNewClient_AndReturn201(){
        Client luiz = createClientObject();
        ResponseEntity<ClientResponseModel> response = restTemplate.postForEntity(getRootURL(), luiz, ClientResponseModel.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Luiz", response.getBody().getName());
        assertEquals("luizin@gmail.com", response.getBody().getEmail());
        assertEquals("(93) 2312-9121", response.getBody().getPhone());
        assertEquals("212.984.212-00", response.getBody().getCpf());
    }

    @Test
    @Order(3)
    public void createNewClient_Returns400(){
        Client client = new Client();
        client.setName("John");
        client.setEmail("john@gmail232.com");
        client.setCpf("32244209122");

        ResponseEntity<ClientResponseModel> response = createPostRequest(client);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(4)
    public void updateExistingClient_Returns200(){
        ResponseEntity<ClientResponseModel> response = createPutRequest();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("384.928.555-21", response.getBody().getCpf());
    }

    @Test
    @Order(5)
    public void deleteExistingClient_Returns204(){
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
