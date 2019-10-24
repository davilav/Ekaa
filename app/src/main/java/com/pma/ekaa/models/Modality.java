package com.pma.ekaa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modality {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("modality_type")
    @Expose
    private Integer modalityType;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getModalityType() {
        return modalityType;
    }

    public void setModalityType(Integer modalityType) {
        this.modalityType = modalityType;
    }

}

