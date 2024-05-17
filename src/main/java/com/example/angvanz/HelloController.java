package com.example.angvanz;

import com.example.angvanz.domain.Agent;
import com.example.angvanz.repository.RepoAgentDB;
import com.example.angvanz.repository.RepoOrderDB;
import com.example.angvanz.repository.RepoProdDB;
import com.example.angvanz.service.Service;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    //PENTRU LOGARE
    @FXML
    private TextField userTF;
    @FXML
    private TextField passTF;

    // BUTOANE
    @FXML 
    Button button;
    @FXML
    Button comanda;
    @FXML
    Button stock;
    @FXML
    Button back;
    @FXML
    Button addAgent;

    private RepoProdDB productRepo;
    private RepoOrderDB orderRepo;
    private RepoAgentDB agentRepo;
    private Service service;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        productRepo = new RepoProdDB();
        orderRepo = new RepoOrderDB();
        agentRepo = new RepoAgentDB();
        service = new Service(productRepo, orderRepo, agentRepo);
    }

//    public boolean login(String username, String password){
//        String query = "SELECT * FROM Agents WHERE username = ? AND password = ?";
//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:agents.sqlite");
//            PreparedStatement pstmt = conn.prepareStatement(query)){
//            pstmt.setString(1, username);
//            pstmt.setString(2, password);
//            ResultSet rs = pstmt.executeQuery();
//            return rs.next();
//        }catch (SQLException e){
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean loginHash(String username, String password){
        /*String query = "SELECT * FROM Agents WHERE username = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:agents.sqlite");
            Statement pstmt = conn.createStatement()){
            //pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("Username").equals(username) && BCrypt.checkpw(password, rs.getString("Password"))){
                    return true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;*/
        Agent agent = agentRepo.getByUsername(username);
        if(BCrypt.checkpw(password, agent.getParola())){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    private void adaugaAgent() throws IllegalAccessException {
        int id =10;
        String username="admin.agent";
        String password="admin";
        Agent agent = new Agent(id, username, password);
        agentRepo.add(agent);
    }
    @FXML
    private void openAdministrare(){
        try{
            var userU = userTF.getText();
            var passU = passTF.getText();
            if(userU.isEmpty() || passU.isEmpty()){
                throw new NumberFormatException("All fields must be filled.");
            }
            if(!userU.endsWith(".agent") || !loginHash(userU, passU)){
                throw  new NumberFormatException("Username-ul sau parola nu este valid!");
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("administrare.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setTitle("Meniu.");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }
    @FXML
    private void openAdministrareStoc(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("administrare-stock.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) stock.getScene().getWindow();
            stage.setTitle("Administrare stock.");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void openCreeareComanda(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creeare-comanda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) comanda.getScene().getWindow();
            stage.setTitle("Creeare comanda.");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void back1(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setTitle("Panel logare");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }

    public void inchidere(ActionEvent actionEvent){
        Platform.exit();
    }
}