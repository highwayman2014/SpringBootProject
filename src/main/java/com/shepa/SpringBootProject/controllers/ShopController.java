package com.shepa.SpringBootProject.controllers;

import com.shepa.SpringBootProject.model.Product;
import com.shepa.SpringBootProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/products/addProduct")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getCategories());
        return "product-form";
    }

    @PostMapping(value = "/products/resultForm")
    public String resultForm(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/products/listProducts";
    }

    @GetMapping(value = "/products/listProducts")
    public String listProducts(Model model,
                               @RequestParam(name = "type", defaultValue = "") String type,
                               @RequestParam("page") Optional<Integer> page){
        if ("Min".equals(type)) {
            model.addAttribute("products", productService.getProductWithMinimalPrice());
        } else if ("Max".equals(type)) {
            model.addAttribute("products", productService.getProductWithMaxPrice());
        } else if ("MinAndMax".equals(type)) {
            model.addAttribute("products", productService.getProductsWithMinAndMaxPrice());
        } else {
            int currentPage = page.orElse(1);
            Page<Product> pageProducts = productService.getPageOfProducts(currentPage - 1);
            model.addAttribute("products", pageProducts.getContent());
            int totalPages = pageProducts.getTotalPages();
            model.addAttribute(totalPages);
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        }

        return "products-page";
    }
    @GetMapping(value = "/products/listProducts/page")
    public String listPageProducts(Model model,
                               @RequestParam(name = "page") int page){

        model.addAttribute("products", productService.getPageOfProducts(page));
        return "products-page";
    }

    @GetMapping(value = "/products/remove")
    public String removeProduct(Model model, @RequestParam(name = "id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/listProducts";
    }
}
