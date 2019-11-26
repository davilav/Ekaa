package com.pma.ekaa.ui.attendance.presenter;

import com.pma.ekaa.data.models.AttendanceDetail;

import java.util.ArrayList;

public interface AttendancePresenter {

    void getAttendanceUser(int userID);

    void getAttendanceUserSuccess(ArrayList<AttendanceDetail> data);

    void responseError(String msg);

}
