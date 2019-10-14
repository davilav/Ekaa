package com.pma.ekaa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendance {

    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("institution")
    @Expose
    private String institution;
    @SerializedName("beneficiary")
    @Expose
    private String beneficiary;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("modality")
    @Expose
    private String modality;


    public Attendance(String lon, String lat, String institution, String beneficiary, String person, String modality) {
        this.lon = lon;
        this.lat = lat;
        this.institution = institution;
        this.beneficiary = beneficiary;
        this.person = person;
        this.modality = modality;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }
}
