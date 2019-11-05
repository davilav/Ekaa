package com.pma.ekaa.ui.login;

import com.pma.ekaa.data.models.RequestUser;

public interface LoginView {

    void loginSuccess(RequestUser requestUser);
    void loginError(String msg);

}
