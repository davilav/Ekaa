package com.pma.ekaa.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceToday {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("beneficiary_id")
    @Expose
    private Integer beneficiaryId;
    @SerializedName("modality_id")
    @Expose
    private Integer modalityId;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Integer beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Integer getModalityId() {
        return modalityId;
    }

    public void setModalityId(Integer modalityId) {
        this.modalityId = modalityId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
