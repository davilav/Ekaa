package com.pma.ekaa.models;

public class Login {
    private String username;
    private String password;

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String user, String password) {
        this.username = user;
        this.password = password;


    }
}
