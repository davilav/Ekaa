package com.pma.ekaa.ui.splash.presenter;

import com.pma.ekaa.data.repository.splash.SplashRepository;
import com.pma.ekaa.data.repository.splash.SplashRepositoryImpl;
import com.pma.ekaa.ui.splash.SplashView;

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;
    private SplashRepository repository;

    public SplashPresenterImpl(SplashView view) {
        this.view = view;
        repository = new SplashRepositoryImpl(this);
    }
}
