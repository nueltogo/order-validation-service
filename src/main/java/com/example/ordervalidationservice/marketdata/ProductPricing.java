package com.example.ordervalidationservice.marketdata;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductPricing{
    public Product[] getProductsExOne() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("https://exchange.matraining.com/md", Product[].class);
        return responseEntity.getBody();
    }
    public Product[] getProductExTwo() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("https://exchange2.matraining.com/md", Product[].class);
        return responseEntity.getBody();
    }
}