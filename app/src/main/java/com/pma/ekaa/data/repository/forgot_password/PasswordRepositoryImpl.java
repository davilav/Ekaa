package com.pma.ekaa.data.repository.forgot_password;

import com.pma.ekaa.data.models.Email;
import com.pma.ekaa.data.models.Password;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordRepositoryImpl implements PasswordRepository {

    private PasswordPresenter presenter;

    public PasswordRepositoryImpl(PasswordPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setRecoveryPassword(Password password) {
        Call<Email> call = ApiClient.getInstance().getApi().password(password);
        call.enqueue(new Callback<Email>() {
            @Override
            public void onResponse(Call<Email> call, Response<Email> response) {
                switch (response.code()){
                    case 200:
                        presenter.responseSuccess(response.body().getDetail());
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
            public void onFailure(Call<Email> call, Throwable t) {
                presenter.responseError("Fallo la conexion con el servidor");
            }
        });
    }
}
