package com.zs.final_assignment.controllers;

import com.zs.final_assignment.entities.Product;
import com.zs.final_assignment.entities.DBConfigFileMissingException;
import com.zs.final_assignment.services.CartService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/cart")
public class CartController extends MainController{
    private final Logger logger = getLogger();
    private CartService cartService;


    public CartController() {
        try {
            cartService  =  new CartService();
        } catch (SQLException | DBConfigFileMissingException | ClassNotFoundException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Map<Product,Integer> getCart(){
        try {
            return cartService.getCart();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public void   addProductToCart(@RequestParam(name = "productId") int id,
                                  @RequestParam(name = "quantity", defaultValue = "1") int quantity){
        try {
            if(cartService.addProductIntoCart(id,quantity)>0){
                logger.log(Level.INFO, "Product inserted into cart.");
            }
            else {
                logger.log(Level.INFO, "Product not inserted into cart.");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    public void deleteProductFromCart(@RequestParam(name = "productId")int id){
        try{
            if(cartService.deleteProductFromCart(id)>0){
                logger.log(Level.INFO, "Product deleted from cart.");
            }
            else {
                logger.log(Level.INFO, "Product not deleted from cart.");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
