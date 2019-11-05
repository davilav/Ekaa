package com.pma.ekaa.data.models;

public class RegisterBeneficiary {

    private int  nationality;
    private int  document_Type;
    private int gender;
    private int agreement;
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
    private String household_code;
    private String id;
    private String profilePhotoLocation;


    public RegisterBeneficiary(int nationality, int documentType, int gender, int agreement, String geolocation, String first_name, String second_name, String surname, String second_surname, String document, String birth_date, String pregnant, String phone, String aditional_information, String household_code) {
        this.nationality = nationality;
        this.document_Type = documentType;
        this.gender = gender;
        this.agreement = agreement;
        this.geolocation = geolocation;
        this.first_name = first_name;
        this.second_name = second_name;
        this.surname = surname;
        this.second_surname = second_surname;
        this.document = document;
        this.birth_date = birth_date;
        this.pregnant = pregnant;
        this.phone = phone;
        this.aditional_information = aditional_information;
        this.household_code = household_code;
    }

    public String getProfilePhotoLocation() {
        return profilePhotoLocation;
    }

    public void setProfilePhotoLocation(String profilePhotoLocation) {
        this.profilePhotoLocation = profilePhotoLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public int getDocumentType() {
        return document_Type;
    }

    public void setDocumentType(int documentType) {
        this.document_Type = documentType;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAgreement() {
        return agreement;
    }

    public void setAgreement(int agreement) {
        this.agreement = agreement;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecond_surname() {
        return second_surname;
    }

    public void setSecond_surname(String second_surname) {
        this.second_surname = second_surname;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
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

    public String getAditional_information() {
        return aditional_information;
    }

    public void setAditional_information(String aditional_information) {
        this.aditional_information = aditional_information;
    }

    public String getHousehold_code() {
        return household_code;
    }

    public void setHousehold_code(String household_code) {
        this.household_code = household_code;
    }
}

