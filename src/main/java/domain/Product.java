package domain;

import java.io.Serializable;

public class Product extends Entity implements Serializable {
    String name;
    Integer quantity;
    Float price;
    public Product(int ID){
        super(ID);
    }

    public Product(int ID, String NAME, Integer QUANTITY, Float PRICE){
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
    public Float getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }
}
