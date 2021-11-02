package com.shepa.controllers;

import com.shepa.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("spring-cloud-external-product-service")
public interface ProductEurekaController {

    @RequestMapping("/get-products")
    List<Product> getProducts();

}
