package repository;


import domain.Entity;
import domain.Product;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoProdDB implements RepoINT{
    private final String url = "jdbc:sqlite:products.sqlite";
    private Connection connection;
    public RepoProdDB(){createTablesIfNotExists();}
    public void createTablesIfNotExists(){
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Products (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT," +
                "Quantity INTEGER," +
                "Price DOUBLE" +
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
        if(entity instanceof Product){
            Product product = (Product) entity;
            String deleteQuery = "DELETE FROM Products WHERE ID=?;";
            try(Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(deleteQuery)){
                pstmt.setInt(1, product.GETid());
                pstmt.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalAccessException("Entity must be Product");
        }
    }
    @Override
    public void add(Entity entity) throws IllegalAccessException {
        if (entity instanceof Product){
            Product product = (Product) entity;
            String insertQuery = "INSERT INTO Products (Name, Quantity, Price) VALUES (?, ?, ?);";
            try(Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(insertQuery)){
                pstmt.setString(1, product.getName());
                pstmt.setInt(2, product.getQuantity());
                pstmt.setDouble(3, product.getPrice());
                pstmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalAccessException("Entity must be Product");
        }
    }
    @Override
    public List<Product> getData(){
        List<Product> data = new ArrayList<>();
        String selectQuery = "SELECT * FROM Products;";

        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery)){
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");

                Product product = new Product(id, name, quantity, price);
                data.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public Product getById(int id) {
        String selectQuery = "SELECT * FROM Products WHERE ID=?;";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(selectQuery)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String name = rs.getString("Name");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");
                return new Product(id, name, quantity, price);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
