package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.InstitutionByPartner;
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
    public void getDataInstitutionByPartner(RequestUser requestUser) {
        repository.getDataInstitutionByPartner(requestUser);
    }

    @Override
    public void getDataUser(RequestUser requestUser) {

    }

    @Override
    public void getInstitutionByPartnerSuccess(ArrayList<InstitutionByPartner> data) {
        view.getInstitutionByPartnerSuccess(data);
    }


    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }
}
