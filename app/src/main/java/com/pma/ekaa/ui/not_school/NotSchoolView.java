package com.pma.ekaa.ui.not_school;

import com.pma.ekaa.data.models.BeneficiaryArray;

public interface NotSchoolView {
    void showLoading();
    void hideLoading();
    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void setRegisterAttendanceSuccess();
    void responseError(String msg);
}
