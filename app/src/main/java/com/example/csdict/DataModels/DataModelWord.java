package com.example.csdict.DataModels;

public class DataModelWord {

    private  String name;
    private  String description;
    private  String ID;
    private  String Apservation;

    @Override
    public String toString() {
        return "DataModelWord{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ID='" + ID + '\'' +
                ", Apservation='" + Apservation + '\'' +
                '}';
    }

    public DataModelWord(String name, String description, String ID, String apservation) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        Apservation = apservation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getApservation() {
        return Apservation;
    }

    public void setApservation(String apservation) {
        Apservation = apservation;
    }
}
