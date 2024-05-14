package domain;

import java.io.Serializable;

public class Product extends Entity implements Serializable {
    String name;
    Integer quantity;
    double price;
    public Product(int ID){
        super(ID);
    }

    public Product(int ID, String NAME, Integer QUANTITY, double PRICE){
        super(ID);
        this.name = NAME;
        this.quantity = QUANTITY;
        this.price = PRICE;
    }

    @Override
    public String toString(){
        return "Product id: " + id + ", name: " + name + ", quantity: " + quantity + ", price: " + price;
    }

    @Override
    public int GETid() {
        return super.GETid();
    }
    public double getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }
    @Override public void SETid(int idd){this.id = idd;}
    public void setPrice(double price1){this.price = price1;}
    public void setName(String name1){this.name = name1;}
    public void setQuantity(int quantity1){this.quantity = quantity1;}

}
