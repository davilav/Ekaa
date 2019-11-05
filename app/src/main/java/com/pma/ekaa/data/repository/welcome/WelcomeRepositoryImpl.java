package com.pma.ekaa.data.repository.welcome;

import com.pma.ekaa.ui.welcome.presenter.WelcomePresenter;

public class WelcomeRepositoryImpl implements WelcomeRepository {

    private WelcomePresenter presenter;

    public WelcomeRepositoryImpl(WelcomePresenter presenter) {
        this.presenter = presenter;
    }
}
