package com.pma.ekaa.ui.login.presenter;

import com.pma.ekaa.data.models.Login;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.repository.login.LoginRepository;
import com.pma.ekaa.data.repository.login.LoginRepositoryImpl;
import com.pma.ekaa.ui.login.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginRepository repository;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        repository = new LoginRepositoryImpl(this);
    }

    @Override
    public void loginUser(String email, String password) {
        Login loginUser = new Login(
                email,
                password
        );
        repository.setLoginUser(loginUser);
    }

    @Override
    public void responseSuccess(String token) {
        RequestUser requestUser = new RequestUser();
        requestUser.setToken(token);
        view.loginSuccess(requestUser);
    }

    @Override
    public void responseError(String msg) {
        view.loginError(msg);
    }
}