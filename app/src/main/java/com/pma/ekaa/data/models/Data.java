package com.pma.ekaa.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("geolocation_type")
    @Expose
    private Integer geolocationType;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("institution_type")
    @Expose
    private Integer institutionType;
    @SerializedName("modality_type")
    @Expose
    private Integer modalityType;
    @SerializedName("geolocation")
    @Expose
    private Integer geolocation;
    @SerializedName("partner")
    @Expose
    private Integer partner;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getGeolocationType() {
        return geolocationType;
    }

    public void setGeolocationType(Integer geolocationType) {
        this.geolocationType = geolocationType;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(Integer institutionType) {
        this.institutionType = institutionType;
    }

    public Integer getModalityType() {
        return modalityType;
    }

    public void setModalityType(Integer modalityType) {
        this.modalityType = modalityType;
    }

    public Integer getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Integer geolocation) {
        this.geolocation = geolocation;
    }

    public Integer getPartner() {
        return partner;
    }

    public void setPartner(Integer partner) {
        this.partner = partner;
    }
}
