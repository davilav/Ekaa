package com.pma.ekaa.data.models;

public class DataUser {
    private Integer pk;
    private String username;
    private String email;
    private String first_name;
    private String last_name;


    // Getter Methods

    public Integer getPk() {
        return pk;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    // Setter Methods

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}