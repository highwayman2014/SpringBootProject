package com.shepa.SpringBootProject.controllers;

import com.shepa.SpringBootProject.content.Product;
import com.shepa.SpringBootProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public String index(){
        return "start-page";
    }

    @GetMapping(value = "/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping(value = "/resultForm")
    public String resultForm(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/products/listProducts";
    }

    @GetMapping(value = "/listProducts")
    @ResponseBody
    public List<Product> listProducts(){
        return productService.getProducts();
    }


}
