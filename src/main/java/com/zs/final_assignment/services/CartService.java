package com.zs.final_assignment.services;

import com.zs.final_assignment.dao.CartDao;
import com.zs.final_assignment.entities.Cart;
import com.zs.final_assignment.entities.Product;
import com.zs.final_assignment.entities.DBConfigFileMissingException;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component
public class CartService {
    private final CartDao cartDao;
    private final ProductService productService;
    public CartService() throws SQLException, DBConfigFileMissingException, ClassNotFoundException , PSQLException {
        cartDao = new CartDao();
        productService = new ProductService();
    }

    public Map<Product,Integer> getCart() throws SQLException {
        String sql = "SELECT * FROM CART;";
        ResultSet resultSet = cartDao.getCartDetails(sql);
        Cart cart = new Cart();
        while (resultSet.next()){
            int productId = resultSet.getInt("PRODUCT_ID");
            int quantity = resultSet.getInt("QUANTITY");
            Product product = productService.getProductById(productId);
            cart.addProduct(product,quantity);
        }
        return cart.getMap();
    }

    public int addProductIntoCart(int id, int quantity) throws SQLException {
        String sql = """
                INSERT INTO CART(PRODUCT_ID,QUANTITY)
                VALUES( ?, ?);""";
        return cartDao.addProduct(id,quantity,sql);

    }

    public int deleteProductFromCart(int id) throws SQLException {
        String sql = """
                DELETE FROM CART
                WHERE PRODUCT_ID = ?""";
        return cartDao.deleteProduct(id,sql);
    }
}
