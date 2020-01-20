package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;
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
    public void getDataDepartment(int partnerID) {
        repository.getDataDepartment(partnerID);
    }

    @Override
    public void getDataTown(int partnerID,int departmentID) {
        repository.getDataTown(partnerID, departmentID);
    }

    @Override
    public void getDataInstitution(int townID) {
        repository.getDataInstitution(townID);
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
