package com.example.angvanz;

import com.example.angvanz.domain.Order;
import com.example.angvanz.repository.RepoAgentDB;
import com.example.angvanz.repository.RepoOrderDB;
import com.example.angvanz.repository.RepoProdDB;
import com.example.angvanz.service.Service;
import javafx.application.Platform;
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

public class ComandaController implements Initializable {
    // PENTRU ADAUGARE PRODUS
    @FXML
    private TableView<Order> tabelo;
    @FXML
    private TextField nameFfield;
    @FXML
    private TextField quantityfield;
    @FXML
    private TextField idPfield;

    private RepoProdDB productRepo;
    private RepoOrderDB orderRepo;
    private RepoAgentDB agentRepo;
    private Service service;

    //BUTOANE
    @FXML
    Button back;

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
        List<Order> orders = service.getAllOrders();

        TableColumn<Order, Integer> columnid = new TableColumn<>("ID");
        columnid.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Order, String> columnCompName = new TableColumn<>("CustomerName");
        columnCompName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Order, String> columnorderAmount = new TableColumn<>("Amount");
        columnorderAmount.setCellValueFactory(cellData-> {
            double orderAmount = cellData.getValue().getOrderAmount();
            return new SimpleStringProperty(orderAmount == 0 ? "n/a" : String.valueOf(orderAmount));
        });

        TableColumn<Order, String> columnIDprod = new TableColumn<>("ProductID");
        columnIDprod.setCellValueFactory(new PropertyValueFactory<>("productID"));

        tabelo.getColumns().clear();
        tabelo.getColumns().addAll(columnid, columnCompName, columnorderAmount, columnIDprod);

        ObservableList<Order> data = FXCollections.observableArrayList(orders);
        tabelo.setItems(data);
    }

    public void addComanda(ActionEvent actionEvent){
        try{
            var nameFText = nameFfield.getText();
            var quantityText = quantityfield.getText();
            var IDprod = idPfield.getText();

            if (nameFText.isEmpty() || IDprod.isEmpty() || quantityText.isEmpty()) {
                throw new NumberFormatException("All fields must be filled");
            }
            double quantity = Double.parseDouble(quantityText);
            int idp = Integer.parseInt(IDprod);

            if(idp < 0 || quantity < 0){
                throw new NumberFormatException("ID produs and Quantity must be greater than 0!");
            }
            service.addOrder(nameFText, quantity, idp);
            showAllrecords();
            nameFfield.clear();
            quantityfield.clear();
            idPfield.clear();

        } catch (IllegalAccessException e) {
            Alert hello = new Alert(Alert.AlertType.WARNING, e.getMessage());
            hello.show();
        }
    }
    public void inchidere(ActionEvent actionEvent){
        Platform.exit();
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
}
