package domain;

import java.io.Serializable;

public class Agent extends Entity implements Serializable {

    String nume;
    String parola;
    public Agent(int id){
        super(id);
    }

    public Agent(int ID, String NUME, String PAROLA){
        super(ID);
        this.nume = NUME;
        this.parola = PAROLA;
    }

    @Override
    public String toString(){
        return "Agent cu id: " + id + " " + ",nume: " + nume + ", parola: " + parola;
    }

    @Override
    public int GETid() {
        return super.GETid();
    }

    public String getNume() {
        return nume;
    }

    public String getParola() {
        return parola;
    }
}
