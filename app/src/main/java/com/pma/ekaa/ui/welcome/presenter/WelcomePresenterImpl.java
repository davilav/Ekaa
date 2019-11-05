package com.pma.ekaa.ui.welcome.presenter;

import com.pma.ekaa.data.repository.welcome.WelcomeRepository;
import com.pma.ekaa.data.repository.welcome.WelcomeRepositoryImpl;
import com.pma.ekaa.ui.welcome.WelcomeView;

public class WelcomePresenterImpl implements WelcomePresenter {

    private WelcomeView view;
    private WelcomeRepository repository;

    public WelcomePresenterImpl(WelcomeView view) {
        this.view = view;
        repository = new WelcomeRepositoryImpl(this);
    }
}
