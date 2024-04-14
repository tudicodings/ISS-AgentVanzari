package domain;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected int id;

    public Entity(int id){
        this.id = id;
    }
    public int GETid(){
        return id;
    }
}
