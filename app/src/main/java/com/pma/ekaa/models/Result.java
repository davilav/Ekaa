package com.pma.ekaa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @SerializedName("document_type")
    @Expose
    private Integer documentType;
    @SerializedName("gender")
    @Expose
    private Integer gender;
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
    @SerializedName("document")
    @Expose
    private String document;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("pregnant")
    @Expose
    private String pregnant;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("aditional_information")
    @Expose
    private String aditionalInformation;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("agreement")
    @Expose
    private String agreement;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPregnant() {
        return pregnant;
    }

    public void setPregnant(String pregnant) {
        this.pregnant = pregnant;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAditionalInformation() {
        return aditionalInformation;
    }

    public void setAditionalInformation(String aditionalInformation) {
        this.aditionalInformation = aditionalInformation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

}