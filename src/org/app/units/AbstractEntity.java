package org.app.units;

public abstract class AbstractEntity {

    private int id;
    //This abstract method is implemented in it's derived classes
    public abstract void autoGenerateId();

    public void setId(int id){ this.id=id; }

    public int getId(){ return id; }
}
