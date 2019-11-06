package com.pma.ekaa.ui.forgot_password.presenter;

import com.pma.ekaa.data.models.Password;
import com.pma.ekaa.data.repository.forgot_password.PasswordRepository;
import com.pma.ekaa.data.repository.forgot_password.PasswordRepositoryImpl;
import com.pma.ekaa.ui.forgot_password.PasswordView;

public class PasswordPresenterImpl implements PasswordPresenter {

    private PasswordView view;
    private PasswordRepository repository;

    public PasswordPresenterImpl(PasswordView view) {
        this.view = view;
        repository = new PasswordRepositoryImpl(this);
    }

    @Override
    public void recoveryPassword(String email) {
        repository.setRecoveryPassword(new Password(email));
    }

    @Override
    public void responseSuccess(String msg) {
        view.passwordSuccess(msg);
    }

    @Override
    public void responseError(String msg) {
        view.passwordError(msg);
    }
}
