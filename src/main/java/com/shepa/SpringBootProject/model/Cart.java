package com.shepa.SpringBootProject.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();
    private double cost = 0;

    public void add(Product product) {
        CartItem existItem = findItem(product);
        if (existItem == null) {
            items.add(new CartItem(product));
        } else {
            existItem.inc();
        }
        cost+= product.getCost();
    }

    public void delete(Product product) {
        CartItem existItem = findItem(product);
        if (existItem != null) {
            cost -= existItem.getCost();
            items.remove(existItem);
        }
    }

    private CartItem findItem(Product product) {
        return items
                .stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getCost() {
        return cost;
    }

    public int getItemsCount() {
        return items.size();
    }

    public String getStringProducts(){
        int num = items.size();
        num = num % 100;
        if (num > 19) {
            num = num % 10;
        }
        switch (num) {
            case 1: {
                return(" товар");
            }
            case 2: case 3: case 4: {
                return(" товара");
            }
            default: {
                return(" товаров");
            }
        }
    }

}
