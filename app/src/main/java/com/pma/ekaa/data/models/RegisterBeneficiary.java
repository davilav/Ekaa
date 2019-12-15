package com.pma.ekaa.data.models;

public class RegisterBeneficiary {

    private String uid;
    private Integer nationality;
    private Integer document_type;
    private Integer gender;
    private Integer household_role;
    private Integer recipient;
    private Integer status;
    private Integer beneficiary_type;
    private Integer migratory_status;
    private Integer marital_status;
    private Integer disability;
    private String first_name;
    private String second_name;
    private String surname;
    private String second_surname;
    private String document;
    private String ethnicity;
    private String birth_date;
    private String pregnant;
    private String phone;
    private String aditional_information;
    private String note;
    private String household_code;
    private String agreement;


    public RegisterBeneficiary(String uid, Integer nationality, Integer document_type, Integer gender, Integer household_role, Integer recipient, Integer status, Integer beneficiary_type, Integer migratory_status, Integer marital_status, Integer disability, String first_name, String second_name, String surname, String second_surname, String document, String ethnicity, String birth_date, String pregnant, String phone, String aditional_information, String note, String household_code, String agreement) {
        this.uid = uid;
        this.nationality = nationality;
        this.document_type = document_type;
        this.gender = gender;
        this.household_role = household_role;
        this.recipient = recipient;
        this.status = status;
        this.beneficiary_type = beneficiary_type;
        this.migratory_status = migratory_status;
        this.marital_status = marital_status;
        this.disability = disability;
        this.first_name = first_name;
        this.second_name = second_name;
        this.surname = surname;
        this.second_surname = second_surname;
        this.document = document;
        this.ethnicity = ethnicity;
        this.birth_date = birth_date;
        this.pregnant = pregnant;
        this.phone = phone;
        this.aditional_information = aditional_information;
        this.note = note;
        this.household_code = household_code;
        this.agreement = agreement;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public Integer getDocument_type() {
        return document_type;
    }

    public void setDocument_type(Integer document_type) {
        this.document_type = document_type;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getHousehold_role() {
        return household_role;
    }

    public void setHousehold_role(Integer household_role) {
        this.household_role = household_role;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBeneficiary_type() {
        return beneficiary_type;
    }

    public void setBeneficiary_type(Integer beneficiary_type) {
        this.beneficiary_type = beneficiary_type;
    }

    public Integer getMigratory_status() {
        return migratory_status;
    }

    public void setMigratory_status(Integer migratory_status) {
        this.migratory_status = migratory_status;
    }

    public Integer getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(Integer marital_status) {
        this.marital_status = marital_status;
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

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHousehold_code() {
        return household_code;
    }

    public void setHousehold_code(String household_code) {
        this.household_code = household_code;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public void setDisability(Integer disability) {
        this.disability = disability;
    }
}