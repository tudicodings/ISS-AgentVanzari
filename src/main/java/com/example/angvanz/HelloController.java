package com.example.angvanz;

import domain.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.RepoAgentDB;
import repository.RepoOrderDB;
import repository.RepoProdDB;
import service.Service;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    boolean admin = false;
    boolean agent  = false;

    //PENTRU LOGARE
    @FXML
    private TextField userTF;
    @FXML
    private TextField passTF;

    // PENTRU ADAUGARE PRODUS
    @FXML
    private TableView<Product> tabelprod;
    @FXML
    private TextField namefield;
    @FXML
    private TextField quantityfield;
    @FXML
    private TextField pricefield;
    @FXML
    private TextField delid;
    private RepoProdDB productRepo;
    private RepoOrderDB orderRepo;
    private RepoAgentDB agentRepo;
    private Service service;

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

    private boolean login(String username, String password){
        String query = "SELECT * FROM Agents WHERE username = ? AND password = ?;";
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
            if(userU.endsWith(".agent") && login(userU, passU)){
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
        try {;
            var numeFirma = numeF.getText();
            var denumireP =  denum.getText();
            //var cantitateC = Integer.parseInt(cant.getText());
            var cantc = cant.getText();
            if (numeFirma.isEmpty() || denumireP.isEmpty() || cantc.isEmpty()) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        productRepo = new RepoProdDB();
        orderRepo = new RepoOrderDB();
        agentRepo = new RepoAgentDB();
        service = new Service(productRepo, orderRepo, agentRepo);
        showAllrecords();
    }
    @FXML
    protected void showAllrecords(){
        List<Product> products = service.getAllProducts();

        TableColumn<Product, String> columnid = new TableColumn<>("ID");
        columnid.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> columnName = new TableColumn<>("Name");
        columnid.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> columnQuantity = new TableColumn<>("Quantity");
        columnQuantity.setCellValueFactory(cellData-> {
            int quantity = cellData.getValue().getQuantity();
            return new SimpleStringProperty(quantity == 0 ? "n/a" : String.valueOf(quantity));
        });

        TableColumn<Product, String> columnPrice = new TableColumn<>("Price");
        columnid.setCellValueFactory(new PropertyValueFactory<>("price"));

        tabelprod.getColumns().clear();
        tabelprod.getColumns().addAll(columnid, columnName, columnQuantity, columnPrice);

        ObservableList<Product> data = FXCollections.observableArrayList(products);
        tabelprod.setItems(data);
    }
    public void addProdus(ActionEvent actionEvent){
        try{
            var nameText = namefield.getText();
            var quantityText = quantityfield.getText();
            var priceText = pricefield.getText();

            if (nameText.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                throw new NumberFormatException("All fields must be filled");
            }
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);

            if(price < 0 || quantity < 0){
                throw new NumberFormatException("Price and Quantity must be greater than 0!");
            }
            service.add(nameText,quantity,price);
            showAllrecords();
            namefield.clear();
            quantityfield.clear();
            pricefield.clear();

        } catch (IllegalAccessException e) {
            Alert hello = new Alert(Alert.AlertType.WARNING, e.getMessage());
            hello.show();
        }
    }
    public void deleteProdus(ActionEvent actionEvent){
        try{
            var iddel = delid.getText();
            if(iddel.isEmpty()){
                throw new NumberFormatException("All fields must be filled!");
            }
            int id = Integer.parseInt(iddel);
            if(id<0){
                throw new NumberFormatException("Id must be greater than 0");
            }
            service.delete(id);
            showAllrecords();
            delid.clear();
        } catch (IllegalAccessException e) {
            Alert hello = new Alert(Alert.AlertType.WARNING, e.getMessage());
            hello.show();
        }
    }
}