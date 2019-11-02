package com.pma.ekaa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Institution {

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

}