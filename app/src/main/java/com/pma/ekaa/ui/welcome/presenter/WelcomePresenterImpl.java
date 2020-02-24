package com.pma.ekaa.ui.welcome.presenter;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.repository.welcome.WelcomeRepository;
import com.pma.ekaa.data.repository.welcome.WelcomeRepositoryImpl;
import com.pma.ekaa.ui.welcome.WelcomeView;

import java.util.ArrayList;

public class WelcomePresenterImpl implements WelcomePresenter {

    private WelcomeView view;
    private WelcomeRepository repository;

    public WelcomePresenterImpl(WelcomeView view) {
        this.view = view;
        repository = new WelcomeRepositoryImpl(this);
    }

    @Override
    public void getPartnersData() {
        repository.getPartnersData();
    }

    @Override
    public void getPartnersSuccess(ArrayList<Data> data) {
        view.getPartnersSuccess(data);
    }

    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }
}
