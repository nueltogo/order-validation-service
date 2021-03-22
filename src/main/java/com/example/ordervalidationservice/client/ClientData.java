package com.example.ordervalidationservice.client;

import com.example.ordervalidationservice.marketdata.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientData {
    public Client getClientData(Client clientId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://tradeenginedb.herokuapp.com/api/v1/client/id";
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(clientId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

        Client answer = restTemplate.postForObject(url, entity, Client.class);
        return  answer;
    }
}
