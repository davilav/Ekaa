package com.pma.ekaa.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstitutionByPartner {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("institution_type")
    @Expose
    private Institution institutionType;
    @SerializedName("partner")
    @Expose
    private Institution partner;
    @SerializedName("modality_type")
    @Expose
    private Institution modalityType;
    @SerializedName("geolocation")
    @Expose
    private Institution geolocation;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Institution getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(Institution institutionType) {
        this.institutionType = institutionType;
    }

    public Institution getPartner() {
        return partner;
    }

    public void setPartner(Institution partner) {
        this.partner = partner;
    }

    public Institution getModalityType() {
        return modalityType;
    }

    public void setModalityType(Institution modalityType) {
        this.modalityType = modalityType;
    }

    public Institution getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Institution geolocation) {
        this.geolocation = geolocation;
    }
}
