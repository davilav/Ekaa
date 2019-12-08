package com.pma.ekaa.ui.school;

import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;

public interface SchoolView {

    void showLoading();
    void hideLoading();

    void getGroupSuccess(ArrayList<Data> data);
    void getClassSuccess(ArrayList<Data> data);
    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void attendanceTodaySuccess(ArrayList<AttendanceToday> response);

    void createBeneficiarySuccess();
    void updateBeneficiarySuccess();

    void responseError(String msg);
}
