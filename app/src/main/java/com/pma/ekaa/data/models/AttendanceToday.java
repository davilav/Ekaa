package com.pma.ekaa.data.models;

public class AttendanceToday {

    private Integer beneficiary_id;
    private Integer modality_id;
    private Integer total;

    public Integer getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(Integer beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public Integer getModality_id() {
        return modality_id;
    }

    public void setModality_id(Integer modality_id) {
        this.modality_id = modality_id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
