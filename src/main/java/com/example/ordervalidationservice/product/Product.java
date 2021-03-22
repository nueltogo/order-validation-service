package com.example.ordervalidationservice.product;

public class Product {
    private String ticker;
    private int quantity;


    public Product() {
    }


    public Product(String ticker, int quantity) {
        this.ticker = ticker;
        this.quantity = quantity;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

