package com.pma.ekaa.ui.register.presenter;

import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.repository.register.RegisterRepository;
import com.pma.ekaa.data.repository.register.RegisterRepositoryImpl;
import com.pma.ekaa.ui.register.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView view;
    private RegisterRepository repository;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        repository = new RegisterRepositoryImpl(this);
    }

    @Override
    public void setRegister(String user, String email, String pass, String pass2, int partner, int rol) {
        repository.setRegister(new Register(
                user,
                email,
                pass,
                pass2,
                partner,
                rol
        ));
    }

    @Override
    public void responseSuccess() {
        view.registerSuccess();
    }

    @Override
    public void responseError(String msg) {
        view.registerError(msg);
    }
}
