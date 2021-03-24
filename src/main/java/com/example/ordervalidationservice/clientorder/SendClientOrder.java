package com.example.ordervalidationservice.clientorder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SendClientOrder {
    public void postOrder(ClientOrder clientOrder) throws JsonProcessingException {
        //String url = "http://localhost:8083/engine";
        String url = "https://trade-engine-app.herokuapp.com/trade/order";
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(clientOrder);
        //System.out.println(requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);
    }

    public void persistToDb(ClientOrder clientOrder) throws JsonProcessingException {
        String url = "https://tradeenginedb.herokuapp.com/api/v1/clientorder/new";
        //String url = "http://localhost:8081/api/v1/clientorder/new";
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(clientOrder);
        //System.out.println(requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

        String answer = restTemplate.postForObject(url, entity, String.class);
    }


}
