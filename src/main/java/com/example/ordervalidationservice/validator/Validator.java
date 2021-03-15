package com.example.ordervalidationservice.validator;

import com.example.ordervalidationservice.marketdata.Product;
import com.example.ordervalidationservice.marketdata.ProductPricing;
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
        ProductPricing productPricing = new ProductPricing();
        List<Product> exOneProducts = Arrays.asList(productPricing.getProductsExOne());
        ArrayList<Product> exOne = new ArrayList<>(exOneProducts);
        List<Product> exTwoProducts = Arrays.asList(productPricing.getProductExTwo());
        ArrayList<Product> exTwo = new ArrayList<>(exTwoProducts);
        List<Product> exOneProduct = exOne.stream().filter(x -> x.getTicker().equals(order.getProduct())).collect(Collectors.toList());
        List<Product> exTwoProduct = exTwo.stream().filter(x -> x.getTicker().equals(order.getProduct())).collect(Collectors.toList());
        if(order.getSide().equals("SELL")){
            double exOnePrice = exOneProduct.get(0).getAskPrice();
            double exTwoPrice = exTwoProduct.get(0).getAskPrice();
//            if()
        }
        if(order.getSide().equals("BUY")){
            double exOnePrice = exOneProduct.get(0).getBidPrice();
            double exTwoPrice = exTwoProduct.get(0).getBidPrice();
        }
        return true;
    }

}
