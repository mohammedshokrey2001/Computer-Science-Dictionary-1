package com.example.csdict.DataModels;

public class DataModelUser {
    private  String name;
    private  String id;
    private  String mail;
    private  String pass;
    private  String phone;
    private  String reset_qu;
    private  String reset_answer;
    private  String fav;
    private  boolean isActive;

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public DataModelUser(String name, String id, String mail, String pass, String phone, String reset_qu, String reset_answer) {
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.pass = pass;
        this.phone = phone;
        this.reset_qu = reset_qu;
        this.reset_answer = reset_answer;
        this.fav = fav;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReset_qu() {
        return reset_qu;
    }

    public void setReset_qu(String reset_qu) {
        this.reset_qu = reset_qu;
    }

    public String getReset_answer() {
        return reset_answer;
    }

    public void setReset_answer(String reset_answer) {
        this.reset_answer = reset_answer;
    }
}
