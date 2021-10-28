package com.shepa.SpringBootProject.controllers;

import com.shepa.SpringBootProject.model.Cart;
import com.shepa.SpringBootProject.services.CartService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    AmqpTemplate template;

    @GetMapping(value = "/products/addToCart")
    private String addToCart(@RequestParam("id") Long productId, HttpServletRequest httpServletRequest){
        String referer = httpServletRequest.getHeader("referer");
        if (productId != null) {
            cartService.addToCart(httpServletRequest.getSession(), productId);
        }
        return "redirect:" + referer;
    }

    @GetMapping(value = "/cart")
    public String openCart(Model model, HttpSession session) {
        model.addAttribute("cart", cartService.getCart(session));
        return "cart";
    }

    @PostMapping(value = "/cart/newOrder")
    public String newOrder(@ModelAttribute("cart") Cart cart, HttpSession session) {
        // Отправим в очередь queueOrders сообщение о новом заказе
        template.convertAndSend("queueOrders", "Новый заказ, ура!");
        return "cart";
    }

    @GetMapping(value = "/cart/delete")
    private String deleteFromCart(@RequestParam("id") Long productId, HttpServletRequest httpServletRequest){
        String referer = httpServletRequest.getHeader("referer");
        if (productId != null) {
            cartService.deleteFromCart(httpServletRequest.getSession(), productId);
        }
        return "redirect:" + referer;
    }
}
