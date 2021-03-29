package com.example.ordervalidationservice.validator;

import com.example.ordervalidationservice.client.Client;
import com.example.ordervalidationservice.client.ClientData;
import com.example.ordervalidationservice.marketdata.Product;
import com.example.ordervalidationservice.marketdata.ProductPricing;
import com.example.ordervalidationservice.marketdata.ProductPricingController;
import com.example.ordervalidationservice.product.ProductData;
import com.fasterxml.jackson.core.JsonProcessingException;
import trade_engine.order_validation_service.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validator {

    private Boolean validateClientBalance(Order order) throws JsonProcessingException {
        Client client = new Client(order.getClientId());
        ClientData clientData = new ClientData();
        Client clientbalance = clientData.getClientData(client);
        return clientbalance.getBalance() >= (order.getPrice() * order.getQuantity());
    }

    private Boolean validateClientPortfolio(Order order){
        ProductData productData = new ProductData();
        List<com.example.ordervalidationservice.product.Product> products = Arrays.asList(productData.products(order.getPortfolioId()));
        ArrayList<com.example.ordervalidationservice.product.Product> productList = new ArrayList(products);
        List<com.example.ordervalidationservice.product.Product> validProduct = productList.stream().filter(x->x.getTicker().equals(order.getProduct())).filter(x->x.getQuantity()>=order.getQuantity()).collect(Collectors.toList());
        System.out.println(validProduct);
        return !validProduct.isEmpty();
    }

    private Boolean validateOrderPrice(Order order){
        double orderPrice = order.getPrice();
        ProductPricing productPricings = new ProductPricing();
        ProductPricingController productPricing = new ProductPricingController(productPricings);
        List<Product> exOneProducts = Arrays.asList(productPricing.getExOneProducts());
        ArrayList<Product> exOne = new ArrayList<>(exOneProducts);
        List<Product> exTwoProducts = Arrays.asList(productPricing.getExTwoProducts());
        ArrayList<Product> exTwo = new ArrayList<>(exTwoProducts);
        List<Product> exOneProduct = exOne.stream().filter(x -> x.getTicker().equals(order.getProduct())).collect(Collectors.toList());
        List<Product> exTwoProduct = exTwo.stream().filter(x -> x.getTicker().equals(order.getProduct())).collect(Collectors.toList());
        if(order.getSide().equals("SELL")){
            double exOnePrice = exOneProduct.get(0).getAskPrice();
            double exTwoPrice = exTwoProduct.get(0).getAskPrice();
            double upperBoundExOne = exOnePrice+exOneProduct.get(0).getMaxPriceShift();
            double lowerBoundExOne = exOnePrice-exOneProduct.get(0).getMaxPriceShift();
            double upperBoundExTwo = exTwoPrice+exTwoProduct.get(0).getMaxPriceShift();
            double lowerBoundExTwo = exTwoPrice-exTwoProduct.get(0).getMaxPriceShift();
            return orderPrice >= lowerBoundExOne && orderPrice <= upperBoundExOne ||  orderPrice >= lowerBoundExTwo && orderPrice <= upperBoundExTwo || order.getQuantity() <= exOneProduct.get(0).getSellLimit() && order.getQuantity() <= exTwoProduct.get(0).getSellLimit();
        }
        else{
            double exOnePrice = exOneProduct.get(0).getBidPrice();
            double exTwoPrice = exTwoProduct.get(0).getBidPrice();
            double upperBoundExOne = exOnePrice+exOneProduct.get(0).getMaxPriceShift();
            double lowerBoundExOne = exOnePrice-exOneProduct.get(0).getMaxPriceShift();
            double upperBoundExTwo = exTwoPrice+exTwoProduct.get(0).getMaxPriceShift();
            double lowerBoundExTwo = exTwoPrice-exTwoProduct.get(0).getMaxPriceShift();
            return orderPrice >= lowerBoundExOne && orderPrice <= upperBoundExOne ||  orderPrice >= lowerBoundExTwo && orderPrice <= upperBoundExTwo || order.getQuantity() <= exOneProduct.get(0).getBuyLimit() && order.getQuantity() <= exTwoProduct.get(0).getBuyLimit();
        }
    }

    public Boolean validate(Order order) throws JsonProcessingException {
        if(order.getSide().equals("SELL")){
            return this.validateClientPortfolio(order) && this.validateOrderPrice(order);
        }
        else{
            return this.validateClientBalance(order) && this.validateOrderPrice(order);
        }
    }

}
