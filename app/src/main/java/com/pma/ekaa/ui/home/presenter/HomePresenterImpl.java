package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.repository.home.HomeRepository;
import com.pma.ekaa.data.repository.home.HomeRepositoryImpl;
import com.pma.ekaa.ui.home.HomeView;

import java.util.ArrayList;

public class HomePresenterImpl implements HomePresenter {

    private HomeView view;
    private HomeRepository repository;

    public HomePresenterImpl(HomeView view) {
        this.view = view;
        repository = new HomeRepositoryImpl(this);
    }

    @Override
    public void getDataDepartment(RequestUser requestUser) {
        repository.getDataDepartment(requestUser);
    }

    @Override
    public void getDataTown(int departmentID, RequestUser requestUser) {
        repository.getDataTown(departmentID, requestUser);
    }

    @Override
    public void getDataInstitution(String partner, int townID, RequestUser requestUser) {
        repository.getDataInstitution(partner, townID, requestUser);
    }

    @Override
    public void getDataUser(RequestUser requestUser) {
        repository.getDataUser(requestUser);
    }

    @Override
    public void setLogout() {
        repository.setLogout();
    }

    @Override
    public void getModalities() {
        repository.getModalities();
    }

    @Override
    public void getDepartmentSuccess(ArrayList<Data> data) {
        view.getDepartmentSuccess(data);
    }

    @Override
    public void getInstitutionSuccess(ArrayList<Data> data) {
        view.getInstitutionSuccess(data);
    }

    @Override
    public void getTownSuccess(ArrayList<Data> data) {
        view.getTownSuccess(data);
    }

    @Override
    public void getDataUserSuccess(DataUser dataUser) {
        view.getDataUserSuccess(dataUser);
    }

    @Override
    public void getLogoutSuccess() {
        view.getLogoutSuccess();
    }

    @Override
    public void getModalitySuccess(ArrayList<Modality> modality) {
        view.getModalitySuccess(modality);
    }

    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }
}
