package com.example.angvanz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class HelloController{
    @FXML
    private Label welcomeText;

    boolean admin = false;
    boolean agent  = false;

    //PENTRU LOGARE
    @FXML
    private TextField userTF;
    @FXML
    private TextField passTF;

    // PENTRU CREEARE COMANDA
    @FXML
    private TextField numeF;
    @FXML
    private TextField denum;
    @FXML
    private TextField cant;

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
    Button add;

    public boolean login(String username, String password){
        String query = "SELECT * FROM Agents WHERE username = ? AND password = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:agents.sqlite");
            PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void openAdministrare(){
        try{
            var userU = userTF.getText();
            var passU = passTF.getText();
            if(userU.isEmpty() || passU.isEmpty()){
                throw new NumberFormatException("All fields must be filled.");
            }
            if(!userU.endsWith(".agent") || !login(userU, passU)){
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