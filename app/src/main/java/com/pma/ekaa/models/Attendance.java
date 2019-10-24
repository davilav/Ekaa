package com.pma.ekaa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendance {

    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("institution")
    @Expose
    private Integer institution;
    @SerializedName("beneficiary")
    @Expose
    private Integer beneficiary;
    @SerializedName("person")
    @Expose
    private Integer person;
    @SerializedName("modality")
    @Expose
    private Integer modality;


    public Attendance(Double lon, Double lat, Integer institution, Integer beneficiary, Integer person, Integer modality) {
        this.lon = lon;
        this.lat = lat;
        this.institution = institution;
        this.beneficiary = beneficiary;
        this.person = person;
        this.modality = modality;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getInstitution() {
        return institution;
    }

    public void setInstitution(Integer institution) {
        this.institution = institution;
    }

    public Integer getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Integer beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Integer getModality() {
        return modality;
    }

    public void setModality(Integer modality) {
        this.modality = modality;
    }

}