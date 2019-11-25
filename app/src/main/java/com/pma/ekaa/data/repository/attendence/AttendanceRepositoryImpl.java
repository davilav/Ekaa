package com.pma.ekaa.data.repository.attendence;


import com.pma.ekaa.ui.attendance.presenter.AttendancePresenter;

public class AttendanceRepositoryImpl implements AttendanceRepository {

    private AttendancePresenter presenter;

    public AttendanceRepositoryImpl(AttendancePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAttendanceUser(int userID) {

    }
}
