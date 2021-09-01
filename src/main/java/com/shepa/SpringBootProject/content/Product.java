package com.shepa.SpringBootProject.content;

import javax.persistence.Entity;

@Entity
public class Product {
    private int id;
    private String title;
    private double cost;

    public Product() {

    }

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
