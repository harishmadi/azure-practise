package com.zs.final_assignment.dao;

import com.zs.final_assignment.entities.DBConfigFileMissingException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CartDao extends MainDao{

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public CartDao() throws ClassNotFoundException, SQLException, DBConfigFileMissingException {
        super();
        try {
            connection = super.getConnection();
            this.createCartTable();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createCartTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS CART
                (PRODUCT_ID INT PRIMARY KEY,
                QUANTITY INT NOT NULL);""";
        statement = connection.createStatement();
        statement.execute(sql);
    }


    public ResultSet getCartDetails(String sql) throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public int addProduct(int productId, int quantity, String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,productId);
        preparedStatement.setInt(2,quantity);

        return preparedStatement.executeUpdate();
    }

    public int deleteProduct(int id,String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
}
