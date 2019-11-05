package com.pma.ekaa.data.repository.splash;

import com.pma.ekaa.ui.splash.presenter.SplashPresenter;

public class SplashRepositoryImpl implements SplashRepository {

    private SplashPresenter presenter;

    public SplashRepositoryImpl(SplashPresenter presenter) {
        this.presenter = presenter;
    }
}
