package com.pma.ekaa.ui.not_school.presenter;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.repository.not_school.NotSchoolRepository;
import com.pma.ekaa.data.repository.not_school.NotSchoolRepositoryImpl;
import com.pma.ekaa.ui.not_school.NotSchoolView;

import java.util.ArrayList;

public class NotSchoolPresenterImpl implements NotSchoolPresenter {

    private NotSchoolView view;
    private NotSchoolRepository repository;

    public NotSchoolPresenterImpl(NotSchoolView view) {
        this.view = view;
        repository = new NotSchoolRepositoryImpl(this);
    }

    @Override
    public void getListBeneficiary(String keyword, int page) {
        repository.getListBeneficiary(keyword, page);
    }

    @Override
    public void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray) {
        view.getListBeneficiarySuccess(beneficiaryArray);
    }

    @Override
    public void setRegisterAttendanceSuccess() {
        view.setRegisterAttendanceSuccess();
    }

    @Override
    public void attendanceTodaySuccess(ArrayList<AttendanceToday> response) {
        view.attendanceTodaySuccess(response);
    }

    @Override
    public void getAttendanceToday(int userID) {
        repository.getAttendanceToday(userID);
    }

    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }

    @Override
    public void setRegisterAttendance(Double longitude, Double latitude, int institution, int userID, int person, int modality) {
        repository.setRegisterAttendance(
                new Attendance(
                        longitude,
                        latitude,
                        institution,
                        userID,
                        person,
                        modality
                )
        );
    }

}
