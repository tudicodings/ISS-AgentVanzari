package repository;

import domain.Agent;
import domain.Product;

import java.util.ArrayList;

public class RepoProd {
    public ArrayList<Product> entities;
    public RepoProd(){this.entities = new ArrayList<>();}
    public ArrayList<Product> getALL(){
        return entities;
    }
    public void add(Product p) throws Exception {
        this.entities.add(p);
    }
}
