package com.shepa.SpringBootProject.services;

import com.shepa.SpringBootProject.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartService {

    @Autowired
    private ProductService productService;

    public void addToCart(HttpSession session, Long productId){
        Cart cart = getCart(session);
        cart.add(productService.getProductById(productId));
    }

    public void deleteFromCart(HttpSession session, Long productId){
        Cart cart = getCart(session);
        cart.delete(productService.getProductById(productId));
    }

    public Cart getCart(HttpSession session) {
        return (Cart) session.getAttribute("cart");
    }

}
