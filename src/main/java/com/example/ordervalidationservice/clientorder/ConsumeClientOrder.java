package com.example.ordervalidationservice.clientorder;

import com.example.ordervalidationservice.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import trade_engine.order_validation_service.GetOrderRequest;
import trade_engine.order_validation_service.GetOrderResponse;
import trade_engine.order_validation_service.Order;

import java.io.IOException;

@Endpoint
public class ConsumeClientOrder {
    private static final String NAMESPACE_URI = "http://trade-engine/order-validation-service";
    private ClientOrder clientOrder;

    @Autowired
    public ConsumeClientOrder() {
    }

    public ClientOrder getClientOrder() {
        return this.clientOrder;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetOrderRequest")
    @ResponsePayload
    public GetOrderResponse getClientOrder(@RequestPayload GetOrderRequest request) throws IOException {
        GetOrderResponse response = new GetOrderResponse();
        Order order = request.getOrder();

        Validator validator = new Validator();

        if(validator.validate(order)){
            response.setOrderId(order.getOrderId());
            response.setValidationStatus("VALIDATED");
            System.out.println(validator.validate(order));
            this.clientOrder = new ClientOrder(order.getOrderId(),order.getProduct(),
                    order.getQuantity(),order.getPrice(),
                    order.getSide(), order.getPortfolioId(),
                    order.getClientId(), "VALIDATED",
                    "PENDING");
            SendClientOrder sendClientOrder = new SendClientOrder();
            sendClientOrder.postOrder(clientOrder);
            sendClientOrder.persistToDb(clientOrder);
        }
        else{
            response.setOrderId(order.getOrderId());
            response.setValidationStatus("REJECTED");
        }
        return response;
    }
}
