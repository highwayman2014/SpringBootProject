package com.shepa.model;

public class CartItem {

    private Product product;
    private int count;
    private double cost;

    public CartItem(Product product) {
        this.product = product;
        this.count = 1;
        this.cost = product.getCost();
    }

    public CartItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void inc(){
        count++;
        cost+= product.getCost();
    }

}
