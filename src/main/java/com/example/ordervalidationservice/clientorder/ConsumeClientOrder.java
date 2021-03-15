package com.example.ordervalidationservice.clientorder;

import com.example.ordervalidationservice.validator.Validator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
    private Order clientOrder;

    @Autowired
    public ConsumeClientOrder() {
    }

    public Order getClientOrder() {
        return this.clientOrder;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getClientOrder(@RequestPayload GetOrderRequest request) throws IOException {
        GetOrderResponse response = new GetOrderResponse();
        Order order = request.getOrder();
//        ClientOrder clientOrder = new ClientOrder(order.getOrderId(), order.getProduct(), order.getPrice(),order.getQuantity(),order.getSide());
        this.clientOrder = order;
        response.setOrder(request.getOrder());
        Validator validator = new Validator();
        System.out.println(validator.validateOrderPrice(order));
        return response;
    }

}
