package com.pma.ekaa.ui.register.presenter;

public interface RegisterPresenter {
    void setRegister(String user, String email, String pass, String pass2, int partner, int rol);

    void responseSuccess();

    void responseError(String msg);
}
