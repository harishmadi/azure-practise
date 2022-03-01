package com.zs.final_assignment.controllers;

import com.zs.final_assignment.entities.DBConfigFileMissingException;
import com.zs.final_assignment.entities.Product;
import com.zs.final_assignment.services.ProductService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends MainController{
    private ProductService productService;
    private Logger logger;

    public ProductController(){
        logger = super.getLogger();
        try {
            productService = new ProductService();
        } catch (SQLException | DBConfigFileMissingException | ClassNotFoundException e) {

        }
    }


    /**
     *
     * @param id product id.
     * @param name product name.
     * @param cost product cost.
     * @param categoryId category id.
     */
    @PostMapping( value = "/create")
    public String  createProduct(@RequestParam(name = "id")int id,
                                 @RequestParam(name = "name")String name,@RequestParam(name = "cost")int cost,
                                 @RequestParam(name = "categoryId",defaultValue = "1")int categoryId){
        try {
            if(productService.createProduct(id,name,cost,categoryId)){
                logger.log(Level.INFO, "Product created.");
            }
            return "Product created";
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
        return null;
    }

    /**
     *
     * @return list of products.
     */
    @GetMapping(value = "/read")
    public List<Product> getAllProducts(){
        try {
            return productService.getAllProducts();
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
        return null;
    }

    /**
     * For updating the product.
     * @param id product id.
     * @param name product name.
     * @param cost product cost.
     * @param categoryId product category id.
     * @return String of message.
     */
    @PutMapping( value = "/update")
    public String updateProduct(@RequestParam(name = "id")int id,
                                @RequestParam(name = "name")String name,@RequestParam(name = "cost")int cost,
                                @RequestParam(name = "categoryId")int categoryId){
        try {
            if(productService.updateProduct(id,name,cost,categoryId)){
                logger.log(Level.INFO,"Product updated.");
            }
            return  "Product updated.";
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
        return  null;
    }

    /**
     *
     * @param id product id that need to be deleted.
     */
    @DeleteMapping(value = "/delete")
    public  void deleteProduct(@RequestParam(value = "id")int id){
        try {
            if(productService.deleteProductById(id)>0){
                logger.log(Level.INFO,"Product deleted.");
            }
            else {
                logger.log(Level.INFO,"Product not deleted.");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
