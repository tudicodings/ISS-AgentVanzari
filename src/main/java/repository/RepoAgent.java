package repository;

import com.almasb.fxgl.core.collection.Array;
import domain.Agent;
import domain.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class RepoAgent  {
    public ArrayList<Agent> entities;
    public RepoAgent(){this.entities = new ArrayList<>();}
    public ArrayList<Agent> getALL(){
        return entities;
    }
    public void add(Agent t) throws Exception {
        this.entities.add(t);
    }

}
