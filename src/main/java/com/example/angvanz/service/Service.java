package com.example.angvanz.service;

import com.example.angvanz.domain.Order;
import com.example.angvanz.domain.Product;
import com.example.angvanz.repository.RepoAgentDB;
import com.example.angvanz.repository.RepoOrderDB;
import com.example.angvanz.repository.RepoProdDB;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Service {
    private RepoProdDB prodRepo;
    private RepoOrderDB orderRepo;
    private RepoAgentDB agentRepo;

    public Service(RepoProdDB prodDB, RepoOrderDB orderDB, RepoAgentDB agentDB){
        this.prodRepo = prodDB;
        this.orderRepo = orderDB;
        this.agentRepo = agentDB;
    }

    public List<Product> getAllProducts(){
        List<Product> products = prodRepo.getData();
        Collections.sort(products, Comparator.comparing(Product::getId));
        return products;
    }

    public List<Order> getAllOrders(){
        List<Order> orders = orderRepo.getData();
        Collections.sort(orders, Comparator.comparing(Order::getId));
        return orders;
    }

    public void add(String name, int quantity, double price) throws IllegalAccessException {
        List<Product> products = prodRepo.getData();
        if(products.isEmpty()){
            int id = 100;
            Product item = new Product(id, name, quantity, price);
            prodRepo.add(item);
        }else{
            List<Product> sortedProducts = getAllProducts();
            int id = sortedProducts.get(sortedProducts.size() - 1).getId() + 1;
            prodRepo.add(new Product(id, name, quantity, price));
        }
    }
    public void delete(int id) throws IllegalAccessException {
        Product existingProduct = (Product) prodRepo.getById(id);
        if(existingProduct != null){
            prodRepo.delete(existingProduct);
        }else {
            System.out.println("Product with ID:" + id + " does not exist.");
        }
    }
    public void addOrder(String customerName, Double amount, int productID) throws IllegalAccessException {
        List<Order> orders = orderRepo.getData();
        if(orders.isEmpty()){
            int id = 100;
            Order order = new Order(id, customerName, amount, productID);
            orderRepo.add(order);
        }else{
            List<Order> sortedOrders = getAllOrders();
            int id = sortedOrders.get(sortedOrders.size() - 1).getId() + 1;
            orderRepo.add(new Order(id, customerName, amount, productID));
        }
    }
}

