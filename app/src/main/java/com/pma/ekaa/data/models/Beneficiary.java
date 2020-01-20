package com.pma.ekaa.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beneficiary {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("second_name")
    @Expose
    private String secondName;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("second_surname")
    @Expose
    private String secondSurname;
    @SerializedName("document_type")
    @Expose
    private Integer documentType;
    @SerializedName("document")
    @Expose
    private String document;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("ethnicity")
    @Expose
    private String ethnicity;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @SerializedName("household_code")
    @Expose
    private String householdCode;

    @SerializedName("disability")
    @Expose
    private Integer disability;

    @SerializedName("user")
    @Expose
    private Integer user;

    public Beneficiary(Integer id, String firstName, String secondName, String surname, String secondSurname, Integer documentType, String document, Integer gender, String ethnicity, String birthDate, Integer nationality, String householdCode, Integer disability,Integer user) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.documentType = documentType;
        this.document = document;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.householdCode = householdCode;
        this.disability = disability;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getHouseholdCode() {
        return householdCode;
    }

    public void setHouseholdCode(String householdCode) {
        this.householdCode = householdCode;
    }

    public Integer getDisability() {
        return disability;
    }

    public void setDisability(Integer disability) {
        this.disability = disability;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
