package com.pma.ekaa.data.repository.register;

import android.content.Intent;
import android.widget.Toast;

import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.models.User;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.register.RegisterActivity;
import com.pma.ekaa.ui.register.presenter.RegisterPresenter;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterRepositoryImpl implements RegisterRepository {

    private RegisterPresenter presenter;

    public RegisterRepositoryImpl(RegisterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setRegister(Register register) {
        Call<Register> call = ApiClient.getInstance().getApi().register(register);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                switch (response.code()){
                    case 200:
                    case 201:
                        presenter.responseSuccess();
                        break;
                    case 400:
                        presenter.responseError("Error");
                        break;
                    default:
                        presenter.responseError("Error1");
                        break;
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                presenter.responseError("Fallo la conexion con el servidor");
            }

        });
    }
}
