package com.zs.final_assignment.entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> map;
    public Cart(){
        map = new HashMap<>();
    }

    public Map<Product,Integer> getMap(){
        return map;
    }

    public void addProduct(Product product,int quantity){
        map.put(product,quantity);
    }

}
