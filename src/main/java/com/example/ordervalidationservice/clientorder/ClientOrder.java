package com.example.ordervalidationservice.clientorder;

public class ClientOrder {
    private String orderId;
    private String product;
    private int quantity;
    private double price;
    private String side;

    public ClientOrder(String orderId, String product, double price, int quantity, String side) {
        this.orderId = orderId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }

    public ClientOrder(String product, double price, int quantity, String side) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }

    public ClientOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "orderId='" + orderId + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", side='" + side + '\'' +
                '}';
    }
}
