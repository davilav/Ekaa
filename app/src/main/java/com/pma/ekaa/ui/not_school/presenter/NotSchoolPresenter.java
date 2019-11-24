package com.pma.ekaa.ui.not_school.presenter;

import com.pma.ekaa.data.models.BeneficiaryArray;

public interface NotSchoolPresenter {
    void getListBeneficiary(String keyword, int page);
    void setRegisterAttendance(Double longitude, Double latitude, int institution, int userID, int person, int modality);

    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void setRegisterAttendanceSuccess();

    void responseError(String msg);
}
