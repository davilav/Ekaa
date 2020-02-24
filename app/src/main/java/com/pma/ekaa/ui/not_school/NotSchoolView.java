package com.pma.ekaa.ui.not_school;

import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;

import java.util.ArrayList;

public interface NotSchoolView {
    void showLoading();
    void hideLoading();
    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void setRegisterAttendanceSuccess();
    void attendanceTodaySuccess(ArrayList<AttendanceToday> response);
    void responseError(String msg);

}
