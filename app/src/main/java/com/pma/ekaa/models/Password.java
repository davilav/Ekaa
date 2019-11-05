package com.pma.ekaa.models;

public class Password {
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Password(String email) {
        this.email = email;
    }
}