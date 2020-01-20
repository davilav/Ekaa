package com.pma.ekaa.data.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @Nullable
    private Integer id;
    @Nullable
    private String uid = null;
    @Nullable
    private Integer nationality;
    @Nullable
    private Integer document_type;
    @Nullable
    private Integer gender;
    @Nullable
    private Integer household_role;
    @Nullable
    private Integer recipient;
    @Nullable
    private Integer status;
    @Nullable
    private Integer beneficiary_type;
    @Nullable
    private Integer migratory_status;
    @Nullable
    private Integer marital_status;
    @Nullable
    private String first_name;
    @Nullable
    private String second_name;
    @Nullable
    private String surname;
    @Nullable
    private String second_surname;
    @Nullable
    private String document;
    @Nullable
    private String ethnicity;
    @Nullable
    private String birth_date;
    @Nullable
    private String pregnant;
    @Nullable
    private String phone;
    @Nullable
    private String aditional_information;
    @Nullable
    private String note;
    @Nullable
    private String household_code;
    @Nullable
    private String agreement;
    @Nullable
    private String registration_date;
    @Nullable
    private String updated_date;
    @Nullable
    private String attendance = null;
    @Nullable
    private String attendance_limit = null;
    @Nullable
    private String color = null;
    @Nullable
    private Integer disability;

    private Integer user;

    @SerializedName("school_program")
    @Expose
    private Integer schoolProgram;




    @SerializedName("school")
    @Expose
    private Integer school;
    @SerializedName("school_class")
    @Expose
    private Integer schoolClass;
    @SerializedName("school_group")
    @Expose
    private Integer schoolGroup;
    @SerializedName("school_class_name")
    @Expose
    private String schoolClassName;
    @SerializedName("belongs_program")
    @Expose
    private Integer belongsProgram;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance( String attendance) {
        this.attendance = attendance;
    }


    public String getAttendance_limit() {
        return attendance_limit;
    }

    public void setAttendance_limit( String attendance_limit) {
        this.attendance_limit = attendance_limit;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public Integer getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Integer schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Integer getSchoolGroup() {
        return schoolGroup;
    }

    public void setSchoolGroup(Integer schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

    public String getSchoolClassName() {
        return schoolClassName;
    }

    public void setSchoolClassName(String schoolClassName) {
        this.schoolClassName = schoolClassName;
    }

    public Integer getBelongsProgram() {
        return belongsProgram;
    }

    public void setBelongsProgram(Integer belongsProgram) {
        this.belongsProgram = belongsProgram;
    }

    @Nullable
    public Integer getDisability() {
        return disability;
    }

    public void setDisability(@Nullable Integer disability) {
        this.disability = disability;
    }

    public Integer getSchoolProgram() {
        return schoolProgram;
    }

    public void setSchoolProgram(Integer schoolProgram) {
        this.schoolProgram = schoolProgram;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}