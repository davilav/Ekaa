package com.pma.ekaa.ui.forgot_password;

public interface PasswordView {

    void passwordSuccess(String msg);
    void passwordError(String msg);

    void showLoading();
    void hideLoading();
}
