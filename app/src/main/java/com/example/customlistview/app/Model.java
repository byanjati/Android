package com.example.customlistview.app;

/**
 * Created by byan on 8/30/2014.
 */
public class Model {

    private String name;
    private boolean check;
    private String qty;

    public String getQty(){
        return this.qty;
    }

    public void setQty(String qty){
        this.qty = qty;
    }

    public Model(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean getSelected(){
        return this.check;
    }

    public void setCheckBox(boolean selected){
        this.check = selected;
    }
}
