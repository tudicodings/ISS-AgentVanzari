//package com.example.angvanz;
//
//import com.example.angvanz.domain.Product;
//import com.example.angvanz.repository.RepoAgentDB;
//import com.example.angvanz.repository.RepoOrderDB;
//import com.example.angvanz.repository.RepoProdDB;
//import com.example.angvanz.service.Service;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class AdministrareSController implements Initializable {
//    // PENTRU ADAUGARE PRODUS
//    @FXML
//    private TableView<Product> tabel;
//    @FXML
//    private TextField namefield;
//    @FXML
//    private TextField quantityfield;
//    @FXML
//    private TextField pricefield;
//    @FXML
//    private TextField delid;
//    private RepoProdDB productRepo;
//    private RepoOrderDB orderRepo;
//    private RepoAgentDB agentRepo;
//    private Service service;
//
//    //BUTOANE
//    @FXML
//    Button back;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle){
//        productRepo = new RepoProdDB();
//        orderRepo = new RepoOrderDB();
//        agentRepo = new RepoAgentDB();
//        service = new Service(productRepo, orderRepo, agentRepo);
//        showAllrecords();
//    }
//    @FXML
//    protected void showAllrecords(){
//        List<Product> products = service.getAllProducts();
//
//        TableColumn<Product, Integer> columnid = new TableColumn<>("ID");
//        columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn<Product, String> columnName = new TableColumn<>("Name");
//        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        TableColumn<Product, String> columnQuantity = new TableColumn<>("Quantity");
//        columnQuantity.setCellValueFactory(cellData-> {
//            int quantity = cellData.getValue().getQuantity();
//            return new SimpleStringProperty(quantity == 0 ? "n/a" : String.valueOf(quantity));
//        });
//
//        TableColumn<Product, String> columnPrice = new TableColumn<>("Price");
//        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//        tabel.getColumns().clear();
//        tabel.getColumns().addAll(columnid, columnName, columnQuantity, columnPrice);
//
//        ObservableList<Product> data = FXCollections.observableArrayList(products);
//        tabel.setItems(data);
//    }
//
//    public void addProdus(ActionEvent actionEvent){
//        try{
//            var nameText = namefield.getText();
//            var quantityText = quantityfield.getText();
//            var priceText = pricefield.getText();
//
//            if (nameText.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
//                throw new NumberFormatException("All fields must be filled");
//            }
//            double price = Double.parseDouble(priceText);
//            int quantity = Integer.parseInt(quantityText);
//
//            if(price < 0 || quantity < 0){
//                throw new NumberFormatException("Price and Quantity must be greater than 0!");
//            }
//            service.add(nameText,quantity,price);
//            showAllrecords();
//            namefield.clear();
//            quantityfield.clear();
//            pricefield.clear();
//            delid.clear();
//
//        } catch (IllegalAccessException e) {
//            Alert hello = new Alert(Alert.AlertType.WARNING, e.getMessage());
//            hello.show();
//        }
//    }
//    public void deleteProdus(ActionEvent actionEvent){
//        try{
//            var iddel = delid.getText();
//            if(iddel.isEmpty()){
//                throw new NumberFormatException("All fields must be filled!");
//            }
//            int id = Integer.parseInt(iddel);
//            if(id<0){
//                throw new NumberFormatException("Id must be greater than 0");
//            }
//            service.delete(id);
//            showAllrecords();
//            delid.clear();
//        } catch (IllegalAccessException e) {
//            Alert hello = new Alert(Alert.AlertType.WARNING, e.getMessage());
//            hello.show();
//        }
//    }
//
//    @FXML
//    private void back2(){
//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("administrare.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) back.getScene().getWindow();
//            stage.setTitle("Meniu.");
//            stage.setScene(scene);
//            stage.show();
//        }catch (Exception e){
//            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
//            alert.show();
//        }
//    }
//}
package com.example.angvanz;

import com.example.angvanz.domain.Product;
import com.example.angvanz.repository.RepoAgentDB;
import com.example.angvanz.repository.RepoOrderDB;
import com.example.angvanz.repository.RepoProdDB;
import com.example.angvanz.service.Service;
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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdministrareSController implements Initializable {
    // PENTRU ADAUGARE PRODUS
    @FXML
    private TableView<Product> tabel;
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

    //BUTOANE
    @FXML
    Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productRepo = new RepoProdDB();
        orderRepo = new RepoOrderDB();
        agentRepo = new RepoAgentDB();
        service = new Service(productRepo, orderRepo, agentRepo);

        TableColumn<Product, Integer> columnid = new TableColumn<>("ID");
        columnid.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Product, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> columnQuantity = new TableColumn<>("Quantity");
        columnQuantity.setCellValueFactory(cellData -> {
            int quantity = cellData.getValue().getQuantity();
            return new SimpleStringProperty(quantity == 0 ? "n/a" : String.valueOf(quantity));
        });

        TableColumn<Product, Double> columnPrice = new TableColumn<>("Price");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tabel.getColumns().clear();
        tabel.getColumns().addAll(columnid, columnName, columnQuantity, columnPrice);

        showAllrecords();
    }

    @FXML
    protected void showAllrecords() {
        List<Product> products = service.getAllProducts();
        ObservableList<Product> data = FXCollections.observableArrayList(products);
        tabel.setItems(data);
    }

    public void addProdus(ActionEvent actionEvent) {
        try {
            var nameText = namefield.getText();
            var quantityText = quantityfield.getText();
            var priceText = pricefield.getText();

            if (nameText.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                throw new NumberFormatException("All fields must be filled");
            }
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);

            if (price < 0 || quantity < 0) {
                throw new NumberFormatException("Price and Quantity must be greater than 0!");
            }
            service.add(nameText, quantity, price);
            showAllrecords();
            namefield.clear();
            quantityfield.clear();
            pricefield.clear();
            delid.clear();

        } catch (IllegalAccessException e) {
            Alert hello = new Alert(Alert.AlertType.WARNING, e.getMessage());
            hello.show();
        }
    }

    public void deleteProdus(ActionEvent actionEvent) {
        try {
            var iddel = delid.getText();
            if (iddel.isEmpty()) {
                throw new NumberFormatException("All fields must be filled!");
            }
            int id = Integer.parseInt(iddel);
            if (id < 0) {
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

    @FXML
    private void back2() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("administrare.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setTitle("Meniu.");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }
    }
}
