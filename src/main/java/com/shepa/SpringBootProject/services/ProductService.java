package com.shepa.SpringBootProject.services;

import com.shepa.SpringBootProject.content.Product;
import com.shepa.SpringBootProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static int PRODUCT_COUNT;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        product.setId(++PRODUCT_COUNT);
        productRepository.addProduct(product);
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }
}
