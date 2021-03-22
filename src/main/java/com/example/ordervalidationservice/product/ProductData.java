package com.example.ordervalidationservice.product;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductData {

    public Product[] products(long portfolioId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://tradeenginedb.herokuapp.com/api/v1/product/portfolioId/" + portfolioId;
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(url,Product[].class);
        return responseEntity.getBody();
    }
}
