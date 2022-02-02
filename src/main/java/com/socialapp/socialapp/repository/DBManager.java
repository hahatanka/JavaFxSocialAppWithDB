package com.socialapp.socialapp.repository;

import java.sql.*;

import org.apache.commons.configuration.*;
public class DBManager {
    private static String user;
    private static String password;
    private static String connectionUrl;
    private static Connection connection;

    private static void getSetDatabaseInfo(){
        PropertiesConfiguration databaseProperties = new PropertiesConfiguration();
        try {
            databaseProperties.load("database.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        String host = databaseProperties.getString("database.host");
        String port = databaseProperties.getString("database.port");
        String dbName = databaseProperties.getString("database.dbName");

        password = databaseProperties.getString("database.password");
        user = databaseProperties.getString("database.userName");
        connectionUrl = host + ":" + port + "/" + dbName + "?serverTimezone=GMT%2B2";
    }

    public static Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()){
                getSetDatabaseInfo();
                connection = DriverManager.getConnection(connectionUrl, user, password);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return connection;
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
       try {
           connection.close();
           preparedStatement.close();
           resultSet.close();
       } catch (Exception ex){
           ex.printStackTrace();
       }
    }
}
