package com.example.angvanz.domain;

import java.io.Serializable;

public class Order extends Entity implements Serializable {
    private String customerName;
    private double orderAmount;
    private int productID;
    public Order(int orderID, String customerName, double orderAmount, int productID){
        super(orderID);
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.productID = productID;
    }

    @Override public int getId() {return super.getId();}
    public String getCustomerName(){return customerName;}
    public double getOrderAmount(){return orderAmount;}
    public int getProductID(){return productID;}
    @Override public void setId(int id){super.setId(id);}
    public void setCustomerName(String customerName){this.customerName = customerName;}
    public void setOrderAmount(double orderAmount){this.orderAmount = orderAmount;}
}