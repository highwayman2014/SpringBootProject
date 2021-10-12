package com.shepa.SpringBootProject.services;

import com.shepa.SpringBootProject.model.Product;
import com.shepa.SpringBootProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public long countProducts(){
        return productRepository.count();
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getProductWithMinimalPrice() {
        List<Product> products = new ArrayList<>();
        products.add(productRepository.findTopByOrderByCostAsc());
        return products;
    }

    public List<Product> getProductWithMaxPrice() {
        List<Product> products = new ArrayList<>();
        products.add(productRepository.findTopByOrderByCostDesc());
        return products;
    }

    public List<Product> getProductsWithMinAndMaxPrice() {
        List<Product> products = new ArrayList<>();
        products.add(productRepository.findTopByOrderByCostAsc());
        products.add(productRepository.findTopByOrderByCostDesc());
        return products;
    }

    public Page<Product> getPageOfProducts(int pageNumber) {
        return productRepository.findAll(PageRequest.of(pageNumber,10));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
