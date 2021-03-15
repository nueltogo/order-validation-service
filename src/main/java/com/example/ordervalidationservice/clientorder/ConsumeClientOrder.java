package com.example.ordervalidationservice.clientorder;

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

    @Autowired
    public ConsumeClientOrder() {
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getCountry(@RequestPayload GetOrderRequest request) throws IOException {
        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(request.getOrder());
//        Order anOrder = convertToPojos(request);
//        System.out.println(anOrder);
        return response;
    }

//    private Order convertToPojos(GetOrderRequest request) throws IOException {
//        XmlMapper xmlMapper = new XmlMapper();
//        System.out.println(request.toString());
//        Order clientOrder = xmlMapper.readValue(request.getOrder().toString(), Order.class);
//        return clientOrder;
//    }
}
