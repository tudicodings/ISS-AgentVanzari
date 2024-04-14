package service;

import domain.Agent;
import domain.Product;
import repository.RepoAgent;
import repository.RepoProd;

import java.util.List;

public class ServProd {
    RepoProd productRepository;
    public ServProd(RepoProd ar){this.productRepository = ar;}
    public List<Product> getallProducts(){return productRepository.getALL();}
    public void addProduct(int ID, String name, int quantity, float price) throws Exception {
        Product product = new Product(ID, name, quantity, price);
        productRepository.add(product);
    }
}
