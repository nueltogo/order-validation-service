package com.example.ordervalidationservice.client;

import java.time.LocalDateTime;

public class Client {

    private long clientId;
    private double balance;

    public Client() {
    }

    public Client(long clientId) {
        this.clientId = clientId;
    }

    public Client(double balance) {
        this.balance = balance;
    }

    public long getClientId() {
        return clientId;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", balance=" + balance +
                '}';
    }
}
