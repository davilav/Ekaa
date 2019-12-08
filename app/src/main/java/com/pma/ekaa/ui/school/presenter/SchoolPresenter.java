package com.pma.ekaa.ui.school.presenter;

import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;

import java.util.ArrayList;

public interface SchoolPresenter {

    void getDataGroup();
    void getDataClass();

    void getListBeneficiary(String keyword, int page);
    void setRegisterAttendance(Double longitude, Double latitude, int institution, int userID, int person, int modality);
    void getAttendanceToday(int userID);

    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void setRegisterAttendanceSuccess();
    void attendanceTodaySuccess(ArrayList<AttendanceToday> response);


    void getGroupSuccess(ArrayList<Data> data);
    void getClassSuccess(ArrayList<Data> data);

    void createBeneficiarySuccess();
    void updateBeneficiarySuccess();

    void responseError(String msg);
}
