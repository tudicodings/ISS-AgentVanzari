package com.example.angvanz.repository;

import com.example.angvanz.domain.Agent;
import com.example.angvanz.domain.Entity;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoAgentDB implements RepoINT{
    private final String url = "jdbc:sqlite:agents.sqlite";
    private Connection connection;
    public RepoAgentDB(){createTablesIfNotExists();}

    public void createTablesIfNotExists(){
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Agents (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT," +
                "Password TEXT" +
                ");";
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(url);
        try{
            if (connection == null || connection.isClosed()){
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
        if(entity instanceof Agent){
            Agent agent = (Agent) entity;
            String deleteQuery = "DELETE FROM Agents WHERE ID=?;";
            try(Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(deleteQuery)){
                pstmt.setInt(1, agent.getId());
                pstmt.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }  else {
            throw new IllegalAccessException("Entity must be Agent");
        }
    }
    @Override
    public void add(Entity entity) throws IllegalAccessException {
        if(entity instanceof Agent){
            Agent agent = (Agent) entity;
            String insertQuery = "INSERT INTO Agents (Username, Password) VALUES (?, ?);";
            try(Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(insertQuery)){
                pstmt.setString(1, agent.getNume());
                pstmt.setString(2, agent.getParola());
                pstmt.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalAccessException("Entity must be Agent");
        }
    }
    @Override
    public List<Agent> getData(){
        List<Agent> data = new ArrayList<>();
        String selectQuery = "SELECT * FROM Agents;";
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery)){
            while(rs.next()){
                int id = rs.getInt("ID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");

                Agent agent = new Agent(id, username, password);
                data.add(agent);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public Entity getById(int id) {
        return null;
    }
}
