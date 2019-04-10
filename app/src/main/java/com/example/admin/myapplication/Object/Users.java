package com.example.admin.myapplication.Object;

import java.io.Serializable;


public class Users implements Serializable {
    private  String uid;
    private  String pass;
    private  String email;
    public Users() {
    }

    public Users(String uid, String name, String email) {
        this.uid = uid;
        this.pass = pass;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Users{" +
                "uid='" + uid + '\'' +
                ", name='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
