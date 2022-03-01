package com.zs.final_assignment.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Product {
    private final int productId;
    private final String name;
    private final int cost;
    private final int categoryId;

    public Product(int id,String name,int cost,int categoryId){
        this.productId = id;
        this.name = name;
        this.cost = cost;
        this.categoryId = categoryId;
    }

    public int getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
