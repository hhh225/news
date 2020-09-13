package com.example.knowledgeaggregation.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String pwd;
    private String email;
    private int type;
    private String password;

    public User(String id, String name, String pwd, String email, int type, String password) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
