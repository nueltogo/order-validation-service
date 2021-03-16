package com.example.ordervalidationservice.clientorder;

import org.springframework.stereotype.Component;
import trade_engine.order_validation_service.Order;

import java.util.ArrayList;

@Component
public class ClientOrderList {
    private ArrayList<Order> orders = new ArrayList<>();

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        this.orders.add(order);
    }
}
