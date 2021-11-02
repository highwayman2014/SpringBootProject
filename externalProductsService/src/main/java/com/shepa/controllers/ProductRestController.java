package com.shepa.controllers;

import com.shepa.model.Product;
import com.shepa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/get-products")
    public List<Product> getProduct() {
        return productService.getProducts();
    }
}
