package com.pma.ekaa.ui.register.presenter;

public interface RegisterPresenter {
    void setRegister(String userName, String email, String pass, String pass2, String firstName, String lastName, int partner, int rol);

    void responseSuccess();

    void responseError(String msg);
}
