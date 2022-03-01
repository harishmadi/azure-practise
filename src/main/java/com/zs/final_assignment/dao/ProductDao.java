package com.zs.final_assignment.dao;

import com.zs.final_assignment.entities.DBConfigFileMissingException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProductDao extends MainDao{
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public ProductDao() throws DBConfigFileMissingException, ClassNotFoundException, SQLException {
        super();
        try {
            connection = super.getConnection();
            this.createProductTable();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createProductTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS PRODUCT
                (ID INT PRIMARY KEY,
                NAME VARCHAR(40) NOT NULL,
                COST INT,
                CATEGORY_ID INT);""";
        statement = connection.createStatement();
        statement.execute(sql);
    }


    public boolean insertRowIntoProduct(int id, String name, int cost, int categoryId, String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3,cost);
        preparedStatement.setInt(4,categoryId);
        return preparedStatement.execute();
    }

    public boolean updateRow(int id, String name, int cost, int categoryId, String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,cost);
        preparedStatement.setInt(3,categoryId);
        preparedStatement.setInt(4,id);
        return preparedStatement.execute();
    }

    public ResultSet getProductDetailsById(String sql, int productId) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,productId);
        return preparedStatement.executeQuery();
    }

    public ResultSet selectAllProducts(String sql) throws SQLException {
        statement = connection.createStatement();
        return  statement.executeQuery(sql);
    }

    public int deleteProduct(int id, String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
}
