package com.shepa.SpringBootProject.controllers;

import com.shepa.SpringBootProject.model.Product;
import com.shepa.SpringBootProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public String index(){
        return "index";
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
    public String listProducts(Model model,
                               @RequestParam(name = "type", defaultValue = "") String type){
        if ("Min".equals(type)) {
            model.addAttribute("products", productService.getProductWithMinimalPrice());
        } else if ("Max".equals(type)) {
            model.addAttribute("products", productService.getProductWithMaxPrice());
        } else if ("MinAndMax".equals(type)) {
            model.addAttribute("products", productService.getProductsWithMinAndMaxPrice());
        } else {
            model.addAttribute("products", productService.getProducts());
        }

        return "products-page";
    }
    @GetMapping(value = "/listProducts/page")
    public String listPageProducts(Model model,
                               @RequestParam(name = "page") int page){

        model.addAttribute("products", productService.getPageOfProducts(page));
        return "products-page";
    }



}
