package com.pma.ekaa.ui.school.presenter;

import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.repository.home.HomeRepository;
import com.pma.ekaa.data.repository.home.HomeRepositoryImpl;
import com.pma.ekaa.data.repository.school.SchoolRepository;
import com.pma.ekaa.data.repository.school.SchoolRepositoryImpl;
import com.pma.ekaa.ui.home.HomeView;
import com.pma.ekaa.ui.school.SchoolActivity;
import com.pma.ekaa.ui.school.SchoolView;

import java.util.ArrayList;

public class SchoolPresenterImpl implements SchoolPresenter{

    private SchoolView view;
    private SchoolRepository repository;

    public SchoolPresenterImpl(SchoolView view) {
        this.view = view;
        repository = new SchoolRepositoryImpl(this);
    }

    @Override
    public void getDataGroup() {
        repository.getDataGroup();
    }

    @Override
    public void getDataClass() {
    repository.getDataClass();
    }

    @Override
    public void getListBeneficiary(String keyword, int page, int idSchool, int idGroup) {
        repository.getListBeneficiary(keyword, page, idSchool, idGroup);
    }

    @Override
    public void setRegisterAttendance(Double longitude, Double latitude, int institution, int userID, int person, int modality) {

    }

    @Override
    public void getAttendanceToday(int userID) {
        repository.getAttendanceToday(userID);
    }

    @Override
    public void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray) {
        view.getListBeneficiarySuccess(beneficiaryArray);
    }

    @Override
    public void setRegisterAttendanceSuccess() {

    }

    @Override
    public void attendanceTodaySuccess(ArrayList<AttendanceToday> response) {
        view.attendanceTodaySuccess(response);
    }

    @Override
    public void getGroupSuccess(ArrayList<Data> data) {
        view.getGroupSuccess(data);
    }

    @Override
    public void getClassSuccess(ArrayList<Data> data) {

    }

    @Override
    public void createBeneficiarySuccess() {
        view.createBeneficiarySuccess();
    }

    @Override
    public void updateBeneficiarySuccess() {
        view.updateBeneficiarySuccess();
    }

    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }
}
