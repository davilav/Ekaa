package com.pma.ekaa.ui.login.presenter;

public interface LoginPresenter {

    void loginUser(String email, String password);

    void responseSuccess(String token);

    void responseError(String msg);

}
