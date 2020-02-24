package com.pma.ekaa.data.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @Nullable
    @SerializedName("id")
    @Expose
    private Integer id;
    @Nullable
    @SerializedName("uid")
    @Expose
    private String uid = null;
    @Nullable
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @Nullable
    @SerializedName("document_type")
    @Expose
    private Integer documentType;
    @Nullable
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @Nullable
    @SerializedName("household_role")
    @Expose
    private Integer householdRole;
    @Nullable
    @SerializedName("recipient")
    @Expose
    private Integer recipient;
    @Nullable
    @SerializedName("status")
    @Expose
    private Integer status;
    @Nullable
    @SerializedName("beneficiary_type")
    @Expose
    private Integer beneficiaryType;
    @Nullable
    @SerializedName("migratory_status")
    @Expose
    private Integer migratoryStatus;
    @Nullable
    @SerializedName("marital_status")
    @Expose
    private Integer maritalStatus;
    @Nullable
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @Nullable
    @SerializedName("second_name")
    @Expose
    private String secondName;
    @Nullable
    @SerializedName("surname")
    @Expose
    private String surname;
    @Nullable
    @SerializedName("second_surname")
    @Expose
    private String secondSurname;
    @Nullable
    @SerializedName("document")
    @Expose
    private String document;
    @Nullable
    @SerializedName("ethnicity")
    @Expose
    private String ethnicity;
    @Nullable
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @Nullable
    @SerializedName("pregnant")
    @Expose
    private String pregnant;
    @Nullable
    @SerializedName("phone")
    @Expose
    private String phone;
    @Nullable
    @SerializedName("aditional_information")
    @Expose
    private String aditionalInformation;
    @Nullable
    @SerializedName("note")
    @Expose
    private String note;
    @Nullable
    @SerializedName("household_code")
    @Expose
    private String householdCode;
    @Nullable
    @SerializedName("agreement")
    @Expose
    private String agreement;
    @Nullable
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @Nullable
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @Nullable
    @SerializedName("attendance")
    @Expose
    private String attendance = null;
    @Nullable
    @SerializedName("attendance_limit")
    @Expose
    private String attendanceLimit = null;
    @Nullable
    @SerializedName("color")
    @Expose
    private String color = null;
    @Nullable
    @SerializedName("disability")
    @Expose
    private Integer disability;
    @SerializedName("user")
    @Expose
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }


    public String getAttendanceLimit() {
        return attendanceLimit;
    }

    public void setAttendanceLimit(String attendanceLimit) {
        this.attendanceLimit = attendanceLimit;
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