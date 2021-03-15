package com.example.ordervalidationservice.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private long productId;
    @JsonProperty("TICKER")
    private String ticker;
    @JsonProperty("LAST_TRADED_PRICE")
    private double lastTradedPrice;
    @JsonProperty("BID_PRICE")
    private double bidPrice;
    @JsonProperty("ASK_PRICE")
    private double askPrice;
    @JsonProperty("MAX_PRICE_SHIFT")
    private int maxPriceShift;
    @JsonProperty("SELL_LIMIT")
    private int sellLimit;
    @JsonProperty("BUY_LIMIT")
    private int buyLimit;

    public Product() {
    }

    public Product(String ticker, double lastTradedPrice, double bidPrice, double askPrice, int maxPriceShift, int sellLimit, int buyLimit) {
        this.ticker = ticker;
        this.lastTradedPrice = lastTradedPrice;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.maxPriceShift = maxPriceShift;
        this.sellLimit = sellLimit;
        this.buyLimit = buyLimit;
    }


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public int getMaxPriceShift() {
        return maxPriceShift;
    }

    public void setMaxPriceShift(int maxPriceShift) {
        this.maxPriceShift = maxPriceShift;
    }

    public int getSellLimit() {
        return sellLimit;
    }

    public void setSellLimit(int sellLimit) {
        this.sellLimit = sellLimit;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(int buyLimit) {
        this.buyLimit = buyLimit;
    }

    @Override
    public String toString() {
        return "Products{" +
                ", productId='" + productId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", lastTradedPrice=" + lastTradedPrice +
                ", bidPrice=" + bidPrice +
                ", askPrice=" + askPrice +
                ", maxPriceShift=" + maxPriceShift +
                ", sellLimit=" + sellLimit +
                ", buyLimit=" + buyLimit +
                '}';
    }
}

