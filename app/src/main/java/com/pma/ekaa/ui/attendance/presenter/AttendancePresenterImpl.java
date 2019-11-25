package com.pma.ekaa.ui.attendance.presenter;

import com.pma.ekaa.data.repository.attendence.AttendanceRepository;
import com.pma.ekaa.data.repository.attendence.AttendanceRepositoryImpl;
import com.pma.ekaa.ui.attendance.AttendanceView;

public class AttendancePresenterImpl implements AttendancePresenter {

    private AttendanceView view;
    private AttendanceRepository repository;

    public AttendancePresenterImpl(AttendanceView view) {
        this.view = view;
        repository = new AttendanceRepositoryImpl(this);
    }

    @Override
    public void getAttendanceUser(int userID) {
        repository.getAttendanceUser(userID);
    }

    @Override
    public void responseError(String msg) {

    }
}
