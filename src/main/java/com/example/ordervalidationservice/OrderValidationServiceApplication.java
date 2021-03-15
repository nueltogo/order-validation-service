package com.example.ordervalidationservice;

import com.example.ordervalidationservice.clientorder.ConsumeClientOrder;
import com.example.ordervalidationservice.validator.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import trade_engine.order_validation_service.Order;

@SpringBootApplication
public class OrderValidationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderValidationServiceApplication.class, args);
		ConsumeClientOrder consumeClientOrder = new ConsumeClientOrder();

	}

}
