package com.example.angvanz.repository;

import com.example.angvanz.domain.Entity;
import com.example.angvanz.domain.Order;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoOrderDB implements RepoINT{
    private final String url = "jdbc:sqlite:orders.sqlite";

    private Connection connection;

    public RepoOrderDB(){createTablesIfNotExists();}

    public void createTablesIfNotExists(){
        String createTableQuery="CREATE TABLE IF NOT EXISTS Orders(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CustomerName TEXT," +
                "Amount DOUBLE," +
                "ProductID INTEGER" +
                ");";
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(url);
        try{
            if(connection == null || connection.isClosed()){
                connection = ds.getConnection();
                Statement stmt = connection.createStatement();
                stmt.execute(createTableQuery);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Entity entity) throws IllegalAccessException {
        if(entity instanceof Order){
            Order order = (Order) entity;
            String deleteQuery = "DELETE FROM Orders WHERE ID=?;";
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(deleteQuery)){
                pstmt.setInt(1, order.getId());
                pstmt.executeUpdate();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }else{
            throw new IllegalAccessException("Entity myst be Order");
        }
    }
    @Override
    public void add(Entity entity) throws IllegalAccessException {
        if(entity instanceof Order){
            Order order = (Order) entity;
            String insertQuery = "INSERT INTO Orders (CustomerName, Amount, ProductID) VALUES (?, ?, ?);";
            try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(insertQuery)){
                pstmt.setString(1, order.getCustomerName());
                pstmt.setDouble(2, order.getOrderAmount());
                pstmt.setInt(3, order.getProductID());
                pstmt.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalAccessException("Entity must be Order");
        }
    }

    @Override
    public List<Order> getData(){
        List<Order> data = new ArrayList<>();
        String selectQuery = "SELECT * FROM Orders;";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)){
            while(rs.next()){
                int id = rs.getInt("ID");
                String customerName = rs.getString("CustomerName");
                double amount = rs.getDouble("Amount");
                int productID = rs.getInt("ProductID");

                Order order = new Order(id, customerName, amount, productID);
                data.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public Entity getById(int id){
        return null;
    }
}


