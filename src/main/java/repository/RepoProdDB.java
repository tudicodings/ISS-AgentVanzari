package repository;


import domain.Agent;
import domain.Product;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepoProdDB extends RepoProd implements RepoINT<Product>{
    private String JDBC_URL;

    private Connection connection = null;

    public RepoProdDB(String JDBC_URL){
        this.JDBC_URL = "jdbc:sqlite:" + JDBC_URL;
        openConnection();
        createtable();
    }
    public void openConnection(){
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);

        try{
            if(connection == null){
                connection = (Connection) ds.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnetion(){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void createtable(){
        try(final Statement stat = connection.createStatement()){
            stat.execute("CREATE TABLE IF NOT EXISTS products(id int, prod_name varchar(400), quantity int, price float);");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> getALL(){
        ArrayList<Product> prods = new ArrayList<>();
        try(PreparedStatement stat = connection.prepareStatement("SELECT * FROM products")){
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getFloat(4));
                prods.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return prods;
    }

    @Override
    public void add(Product prod){
        try(PreparedStatement stat = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?);")){
            stat.setInt(1,prod.GETid());
            stat.setString(2,prod.getName());
            stat.setInt(3,prod.getQuantity());
            stat.setFloat(4,prod.getPrice());
        }catch (SQLException e){
            // throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product prod){
        String sql = "DELETE FROM products WHERE id = ?";
        try(PreparedStatement stat = connection.prepareStatement(sql)){
            stat.setInt(1,prod.GETid());
            stat.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
