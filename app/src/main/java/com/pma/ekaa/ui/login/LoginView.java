package com.pma.ekaa.ui.login;

import com.pma.ekaa.data.models.UserLog;

public interface LoginView {

    void loginSuccess(UserLog response);
    void loginError(String msg);

}
