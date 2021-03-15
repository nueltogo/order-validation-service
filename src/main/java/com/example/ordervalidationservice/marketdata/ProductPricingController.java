package com.example.ordervalidationservice.marketdata;

//import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/products")
public class ProductPricingController {
    private final ProductPricing productPricing;

    @Autowired
    public ProductPricingController(ProductPricing productPricing) {
        this.productPricing = productPricing;
    }

    @GetMapping("/ex1/all")
    public Product[] getExOneProducts(){
        return productPricing.getProductsExOne();
    }

    @GetMapping("/ex2/all")
    public Product[] getExTwoProducts(){
        return productPricing.getProductExTwo();
    }
}
