package com.example.angvanz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
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
    private TextField idCom;
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

    @FXML
    private void openAdministrare(){
        try{
            var userU = userTF.getText();
            var passU = passTF.getText();
            if(userU.isEmpty() || passU.isEmpty()){
                throw new NumberFormatException("All fields must be filled.");
            }
            /*if(passU.endsWith("1")){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Sunteti logat ca admin");
                alert.show();
                admin = true;
                agent = false;
            }else if(passU.endsWith("2")){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Sunteti logat ca agent");
                alert.show();
                agent = true;
                admin = false;
            }
            //((!passU.endsWith("1") && passU.endsWith("2")) ||( !passU.endsWith("2") && passU.endsWith("1")))
            if(!passU.endsWith("1") && !passU.endsWith("2")){
                throw new NumberFormatException("Wrong password");
            }*/
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
            if(admin){
                throw new NumberFormatException("Not an employee!");
            }
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
            if(admin){
                throw new NumberFormatException("Not an employee!");
            }
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

    @FXML
    private void back2(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("administrare.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setTitle("Meniu.");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }
    @FXML
    private void addComanda(){
        try {
            var idc = idCom.getText();
            //var idcomanda = Integer.parseInt(idCom.getText());
            var numeFirma = numeF.getText();
            var denumireP =  denum.getText();
            //var cantitateC = Integer.parseInt(cant.getText());
            var cantc = cant.getText();
            if ( idc.isEmpty() || numeFirma.isEmpty() || denumireP.isEmpty() || cantc.isEmpty()) {
                throw new NumberFormatException("You can't leave empty fields!");
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }
    @FXML
    private void addComandaFinal(){
        try{
            throw new NumberFormatException("Comanda a fost adaugata!");
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();

        }
    }
}