package com.pma.ekaa.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBeneficiary {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @SerializedName("document_type")
    @Expose
    private Integer documentType;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("household_role")
    @Expose
    private Integer householdRole;
    @SerializedName("recipient")
    @Expose
    private Integer recipient;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("beneficiary_type")
    @Expose
    private Integer beneficiaryType;
    @SerializedName("migratory_status")
    @Expose
    private Integer migratoryStatus;
    @SerializedName("marital_status")
    @Expose
    private Integer maritalStatus;
    @SerializedName("disability")
    @Expose
    private Integer disability;
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
    @SerializedName("ethnicity")
    @Expose
    private String ethnicity;
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
    @SerializedName("household_code")
    @Expose
    private String householdCode;
    @SerializedName("agreement")
    @Expose
    private String agreement;
    @SerializedName("user")
    @Expose
    private Integer user;


    public RegisterBeneficiary(String uid, Integer nationality, Integer documentType, Integer gender, Integer householdRole, Integer recipient, Integer status, Integer beneficiaryType, Integer migratoryStatus, Integer maritalStatus, Integer disability, String firstName, String secondName, String surname, String secondSurname, String document, String ethnicity, String birthDate, String pregnant, String phone, String aditionalInformation, String note, String householdCode, String agreement, Integer user) {
        this.uid = uid;
        this.nationality = nationality;
        this.documentType = documentType;
        this.gender = gender;
        this.householdRole = householdRole;
        this.recipient = recipient;
        this.status = status;
        this.beneficiaryType = beneficiaryType;
        this.migratoryStatus = migratoryStatus;
        this.maritalStatus = maritalStatus;
        this.disability = disability;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.document = document;
        this.ethnicity = ethnicity;
        this.birthDate = birthDate;
        this.pregnant = pregnant;
        this.phone = phone;
        this.aditionalInformation = aditionalInformation;
        this.note = note;
        this.householdCode = householdCode;
        this.agreement = agreement;
        this.user = user;
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

    public Integer getHouseholdRole() {
        return householdRole;
    }

    public void setHouseholdRole(Integer householdRole) {
        this.householdRole = householdRole;
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

    public Integer getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(Integer beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public Integer getMigratoryStatus() {
        return migratoryStatus;
    }

    public void setMigratoryStatus(Integer migratoryStatus) {
        this.migratoryStatus = migratoryStatus;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public String getHouseholdCode() {
        return householdCode;
    }

    public void setHouseholdCode(String householdCode) {
        this.householdCode = householdCode;
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

    public Integer getDisability() {
        return disability;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}