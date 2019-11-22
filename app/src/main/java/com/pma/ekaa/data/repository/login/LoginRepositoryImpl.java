package com.pma.ekaa.data.repository.login;

import com.pma.ekaa.data.models.Login;
import com.pma.ekaa.data.models.UserLog;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.login.presenter.LoginPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositoryImpl implements LoginRepository {

    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoginUser(Login loginUser) {

        Call<UserLog> call = ApiClient.getInstance().getApi().login(loginUser);
        call.enqueue(new Callback<UserLog>() {
            @Override
            public void onResponse(Call<UserLog> call, Response<UserLog> response) {
                if (response.code() == 200) {
                    presenter.responseSuccess(response.body());
                } else if (response.code() == 400) {
                    //Toasty.error(LoginActivity.this, "Error al iniciar sesion.", Toast.LENGTH_SHORT, true).show();
                    presenter.responseError("Error al iniciar sesion.");
                } else {
                    //Toasty.error(LoginActivity.this, "Error al iniciar sesion.", Toast.LENGTH_SHORT, true).show();
                    presenter.responseError("Error al iniciar sesion.");
                }
            }

            @Override
            public void onFailure(Call<UserLog> call, Throwable t) {
                presenter.responseError("Fallo la conexion con el servidor");
                //Toasty.warning(LoginActivity.this, "Fallo la conexion con el servidor", Toast.LENGTH_SHORT, true).show();
            }
        });


    }
}
