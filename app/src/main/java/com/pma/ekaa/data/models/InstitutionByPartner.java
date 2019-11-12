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
    private Data institutionType;
    @SerializedName("partner")
    @Expose
    private Data partner;
    @SerializedName("modality_type")
    @Expose
    private Data modalityType;
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

    public Data getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(Data institutionType) {
        this.institutionType = institutionType;
    }

    public Data getPartner() {
        return partner;
    }

    public void setPartner(Data partner) {
        this.partner = partner;
    }

    public Data getModalityType() {
        return modalityType;
    }

    public void setModalityType(Data modalityType) {
        this.modalityType = modalityType;
    }

    public Institution getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Institution geolocation) {
        this.geolocation = geolocation;
    }
}
