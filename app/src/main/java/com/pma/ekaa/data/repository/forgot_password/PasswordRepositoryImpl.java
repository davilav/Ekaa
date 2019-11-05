package com.pma.ekaa.data.repository.forgot_password;

import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenter;

public class PasswordRepositoryImpl implements PasswordRepository {

    private PasswordPresenter presenter;

    public PasswordRepositoryImpl(PasswordPresenter presenter) {
        this.presenter = presenter;
    }
}
