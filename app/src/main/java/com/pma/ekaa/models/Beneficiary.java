package com.pma.ekaa.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Beneficiary {

    private int id;
    private float nationality;
    private float document_type;
    private float gender;
    private String geolocation;
    private String first_name;
    private String second_name;
    private String surname;
    private String second_surname;
    private String document;
    private String birth_date;
    private String pregnant;
    private String phone;
    private String aditional_information;
    private String note;
    private String agreement;
    private String registration_date;


    // Getter Methods


    public int getId() {
        return id;
    }

    public float getNationality() {
        return nationality;
    }

    public float getDocument_type() {
        return document_type;
    }

    public float getGender() {
        return gender;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecond_surname() {
        return second_surname;
    }

    public String getDocument() {
        return document;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getPregnant() {
        return pregnant;
    }

    public String getPhone() {
        return phone;
    }

    public String getAditional_information() {
        return aditional_information;
    }

    public String getNote() {
        return note;
    }

    public String getAgreement() {
        return agreement;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    // Setter Methods


    public void setId(int id) {
        this.id = id;
    }

    public void setNationality(float nationality) {
        this.nationality = nationality;
    }

    public void setDocument_type(float document_type) {
        this.document_type = document_type;
    }

    public void setGender(float gender) {
        this.gender = gender;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSecond_surname(String second_surname) {
        this.second_surname = second_surname;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setPregnant(String pregnant) {
        this.pregnant = pregnant;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAditional_information(String aditional_information) {
        this.aditional_information = aditional_information;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }


}
