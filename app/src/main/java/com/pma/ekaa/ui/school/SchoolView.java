package com.pma.ekaa.ui.school;

import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;

import java.util.ArrayList;

public interface SchoolView {

    void showLoading();
    void hideLoading();

    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void attendanceTodaySuccess(ArrayList<AttendanceToday> response);
    void setRegisterAttendanceSuccess();
    void createBeneficiarySuccess();
    void updateBeneficiarySuccess();

    void responseError(String msg);
}
