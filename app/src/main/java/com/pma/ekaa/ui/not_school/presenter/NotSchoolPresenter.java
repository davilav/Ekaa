package com.pma.ekaa.ui.not_school.presenter;

import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;

import java.util.ArrayList;

public interface NotSchoolPresenter {
    void getListBeneficiary(String keyword, int page);
    void setRegisterAttendance(Double longitude, Double latitude, int institution, int userID, int person, int modality);
    void getAttendanceToday(int userID);

    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void setRegisterAttendanceSuccess();
    void attendanceTodaySuccess(ArrayList<AttendanceToday> response);

    void responseError(String msg);

}
