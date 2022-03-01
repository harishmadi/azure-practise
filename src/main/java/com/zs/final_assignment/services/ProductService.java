package com.zs.final_assignment.services;

import com.zs.final_assignment.dao.ProductDao;
import com.zs.final_assignment.entities.Product;
import com.zs.final_assignment.entities.DBConfigFileMissingException;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private final ProductDao productDao;
    public ProductService() throws SQLException, DBConfigFileMissingException, ClassNotFoundException , PSQLException {
        productDao = new ProductDao();
    }
    public boolean createProduct(int id, String name, int cost, int categoryId) throws SQLException{
        String sql= """
                INSERT INTO PRODUCT
                (ID,NAME,COST,CATEGORY_ID)
                VALUES(?,?,?,?);""";
        return productDao.insertRowIntoProduct(id,name,cost,categoryId,sql);
    }

    public boolean updateProduct(int id, String name, int cost, int categoryId) throws SQLException{
        String sql = """
                UPDATE PRODUCT
                SET NAME = ?, COST = ?, CATEGORY_ID = ?
                WHERE ID = ?""";
        return productDao.updateRow(id,name,cost,categoryId,sql);
    }

    public Product getProductById(int productId) throws SQLException {
        String sql = """
                SELECT * FROM PRODUCT
                WHERE ID=?;""";
        ResultSet resultSet = productDao.getProductDetailsById(sql,productId);
        if (resultSet.next()){
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            int cost = resultSet.getInt("COST");
            int categoryId = resultSet.getInt("CATEGORY_ID");
            return new Product(id,name,cost,categoryId);
        }
        return null;
    }

    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM PRODUCT;";
        ResultSet resultSet = productDao.selectAllProducts(sql);
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()){
            int productId = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            int cost = resultSet.getInt("COST");
            int categoryId = resultSet.getInt("CATEGORY_ID");
            productList.add(new Product(productId,name,cost,categoryId));
        }
        return productList;
    }

    public int deleteProductById(int id) throws SQLException {
        String sql = "DELETE FROM PRODUCT WHERE ID = ?";
        return productDao.deleteProduct(id,sql);
    }
}
