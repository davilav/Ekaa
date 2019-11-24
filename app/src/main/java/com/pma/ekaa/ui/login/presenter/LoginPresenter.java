package com.pma.ekaa.ui.login.presenter;

import com.pma.ekaa.data.models.UserLog;

public interface LoginPresenter {

    void loginUser(String email, String password);

    void responseSuccess(UserLog response);

    void responseError(String msg);

}
