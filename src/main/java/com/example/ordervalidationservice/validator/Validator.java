package com.example.ordervalidationservice.validator;

import com.example.ordervalidationservice.marketdata.Product;
import com.example.ordervalidationservice.marketdata.ProductPricing;
import com.example.ordervalidationservice.marketdata.ProductPricingController;
import trade_engine.order_validation_service.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validator {

    public Boolean validateClientBalance(String clientId){
        return true;
    }

    public Boolean validateClientPortfolio(String clientId){
        return true;
    }

    public Boolean validateOrderPrice(Order order){
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
            double upperBoundExOne = exOnePrice+(exOnePrice*0.1);
            double lowerBoundExOne = exOnePrice-(exOnePrice*0.1);
            double upperBoundExTwo = exTwoPrice+(exTwoPrice*0.1);
            double lowerBoundExTwo = exTwoPrice-(exTwoPrice*0.1);
            return orderPrice >= lowerBoundExOne && orderPrice <= upperBoundExOne ||  orderPrice >= lowerBoundExTwo && orderPrice <= upperBoundExTwo;
        }
        else{
            double exOnePrice = exOneProduct.get(0).getBidPrice();
            double exTwoPrice = exTwoProduct.get(0).getBidPrice();
            double upperBoundExOne = exOnePrice+(exOnePrice*0.1);
            double lowerBoundExOne = exOnePrice-(exOnePrice*0.1);
            double upperBoundExTwo = exTwoPrice+(exTwoPrice*0.1);
            double lowerBoundExTwo = exTwoPrice-(exTwoPrice*0.1);
            return orderPrice >= lowerBoundExOne && orderPrice <= upperBoundExOne ||  orderPrice >= lowerBoundExTwo && orderPrice <= upperBoundExTwo;
        }
    }

}
