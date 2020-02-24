package com.pma.ekaa.ui.register;

public interface RegisterView {

    void registerSuccess();
    void registerError(String msg);

    void showLoading();
    void hideLoading();

}
