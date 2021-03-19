package com.example.ordervalidationservice.clientorder;

import java.time.LocalDate;

public class ClientOrder {
    private long orderId;
    private String product;
    private int quantity;
    private double price;
    private String side;
    private long portfolioId;
    private long clientId;
    private String validationStatus;
    private String Status;
    private LocalDate createAt;

    public ClientOrder() {
    }

    public ClientOrder(String product, int quantity, double price, String side, long portfolioId, long clientId, String validationStatus, String status, LocalDate createAt) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.portfolioId = portfolioId;
        this.clientId = clientId;
        this.validationStatus = validationStatus;
        Status = status;
        this.createAt = createAt;
    }

    public ClientOrder(long orderId, String product, int quantity, double price, String side, long portfolioId, long clientId, String validationStatus, String status, LocalDate createAt) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.portfolioId = portfolioId;
        this.clientId = clientId;
        this.validationStatus = validationStatus;
        Status = status;
        this.createAt = createAt;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "orderId=" + orderId +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", portfolioId=" + portfolioId +
                ", clientId=" + clientId +
                ", validationStatus='" + validationStatus + '\'' +
                ", Status='" + Status + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
