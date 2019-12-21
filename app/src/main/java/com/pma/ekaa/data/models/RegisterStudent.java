package com.pma.ekaa.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterStudent {

    @SerializedName("beneficiary")
    @Expose
    private Beneficiary beneficiary;
    @SerializedName("school")
    @Expose
    private Integer school;

    @SerializedName("school_program")
    @Expose
    private Integer schoolProgram;
    @SerializedName("school_group")
    @Expose
    private Integer schoolGroup;
    @SerializedName("school_class")
    @Expose
    private Integer schoolClass;
    @SerializedName("belongs_program")
    @Expose
    private String belongsProgram;

    public RegisterStudent(Beneficiary beneficiary, Integer school, Integer schoolGroup, Integer schoolClass, String belongsProgram, Integer schoolProgram) {
        this.beneficiary = beneficiary;
        this.school = school;
        this.schoolGroup = schoolGroup;
        this.schoolClass = schoolClass;
        this.belongsProgram = belongsProgram;
        this.schoolProgram = schoolProgram;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public Integer getSchoolGroup() {
        return schoolGroup;
    }

    public void setSchoolGroup(Integer schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

    public Integer getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Integer schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getBelongsProgram() {
        return belongsProgram;
    }

    public void setBelongsProgram(String belongsProgram) {
        this.belongsProgram = belongsProgram;
    }

}
