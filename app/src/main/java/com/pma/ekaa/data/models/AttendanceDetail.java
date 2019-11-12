package com.pma.ekaa.data.models;

public class AttendanceDetail {

    private String institution;
    private String geolocation;
    private String partner;
    private Integer modality_type_id;
    private String modality_type;
    private String modality;
    private String color;
    private Integer beneficiary_id;
    private Integer attendance;
    private Integer year;
    private Integer month;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Integer getModality_type_id() {
        return modality_type_id;
    }

    public void setModality_type_id(Integer modality_type_id) {
        this.modality_type_id = modality_type_id;
    }

    public String getModality_type() {
        return modality_type;
    }

    public void setModality_type(String modality_type) {
        this.modality_type = modality_type;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(Integer beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
