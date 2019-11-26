package com.pma.ekaa.ui.attendance;

import com.pma.ekaa.data.models.AttendanceDetail;

import java.util.ArrayList;

public interface AttendanceView {

    void showLoading();
    void hideLoading();
    void getAttendanceUserSuccess(ArrayList<AttendanceDetail> data);
    void responseError(String msg);

}
