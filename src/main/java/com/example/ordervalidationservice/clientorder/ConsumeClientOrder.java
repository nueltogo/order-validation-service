package com.example.ordervalidationservice.clientorder;

import com.example.ordervalidationservice.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import redis.clients.jedis.Jedis;
import trade_engine.order_validation_service.GetOrderRequest;
import trade_engine.order_validation_service.GetOrderResponse;
import trade_engine.order_validation_service.Order;

import java.io.IOException;

@Endpoint
public class ConsumeClientOrder {
    private static final String NAMESPACE_URI = "http://trade-engine/order-validation-service";
    private ClientOrder clientOrder;

    Jedis jedis = new Jedis("redis-17587.c92.us-east-1-3.ec2.cloud.redislabs.com", 17587);

    @Autowired
    public ConsumeClientOrder() {
        jedis.auth("rLAKmB4fpXsRZEv9eJBkbddhTYc1RWtK");
    }

    public ClientOrder getClientOrder() {
        return this.clientOrder;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetOrderRequest")
    @ResponsePayload
    public GetOrderResponse getClientOrder(@RequestPayload GetOrderRequest request) throws IOException {

        GetOrderResponse response = new GetOrderResponse();
        Order order = request.getOrder();

        this.clientOrder = new ClientOrder(order.getOrderId(),order.getProduct(),
                order.getQuantity(),order.getPrice(),
                order.getSide(), order.getPortfolioId(),
                order.getClientId(), "PENDING",
                "PENDING");

        jedis.publish("report-message", clientOrder.toString()+" has been received into the order validation service");

        Validator validator = new Validator();

        if(validator.validate(order)){
            response.setOrderId(order.getOrderId());
            response.setValidationStatus("VALIDATED");
            System.out.println(validator.validate(order));
            this.clientOrder.setValidationStatus("VALIDATED");


            SendClientOrder sendClientOrder = new SendClientOrder();
            long clientOrderId = sendClientOrder.persistToDb(clientOrder);
            clientOrder.setOrderId(clientOrderId);
            jedis.publish("report-message", clientOrder.toString()+" has been validated the order validation service");
            sendClientOrder.postOrder(clientOrder);
            jedis.publish("report-message", clientOrder.toString()+" has been sent to the trade engine service");

        }
        else{
            response.setOrderId(order.getOrderId());
            response.setValidationStatus("REJECTED");
            this.clientOrder.setValidationStatus("REJECTED");
            jedis.publish("report-message", clientOrder.toString()+" has been validated the order validated service");
        }
        return response;
    }
}
