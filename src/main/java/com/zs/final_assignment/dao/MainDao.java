package com.zs.final_assignment.dao;

import com.zs.final_assignment.entities.DBConfigFileMissingException;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Repository
public class MainDao {
    private final Connection connection;

    /**
     * Default constructor.
     * @throws SQLException when sql statement execution didn't go well.
     * @throws DBConfigFileMissingException when dbconfig.properties file is missing.
     * @throws ClassNotFoundException when driver not found.
     */
    protected MainDao() throws DBConfigFileMissingException, ClassNotFoundException, SQLException, PSQLException {
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties();
        String cwd = System.getProperty("user.dir");
        String fileName = cwd + "/src/main/resources/dbconfig.properties";
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);
        } catch (Exception e) {
            throw new DBConfigFileMissingException("Properties File not found.");
        }
        String jdbcUrl = properties.getProperty("db.url");
        String dataBaseName = properties.getProperty("db.name");
        String userName = properties.getProperty("db.username");
        String userPassword = properties.getProperty("db.password");
        String url = jdbcUrl + dataBaseName;
        connection = DriverManager.getConnection(url, userName, userPassword);
    }

    protected Connection getConnection(){
        return connection;
    }

}
