package com.pma.ekaa.ui.register.presenter;

import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.models.User;
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
    public void setRegister(String userName, String email, String pass, String pass2, String firstName, String lastName, int partner, int rol) {
        Register register = new Register();
        register.setUser(new User(userName, email, firstName, lastName, pass, pass2));
        register.setRol(rol);
        register.setPartner(partner);
        repository.setRegister(register);
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
