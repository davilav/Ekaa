package com.pma.ekaa.ui.forgot_password.presenter;

public interface PasswordPresenter {

    void recoveryPassword(String email);

    void responseSuccess(String msg);

    void responseError(String msg);

}
