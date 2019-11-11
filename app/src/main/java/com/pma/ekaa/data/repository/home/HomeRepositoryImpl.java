package com.pma.ekaa.data.repository.home;

import android.content.Intent;
import android.widget.Toast;

import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.models.UserLog;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.home.presenter.HomePresenter;
import com.pma.ekaa.ui.welcome.WelcomeActivity;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeRepositoryImpl implements HomeRepository {

    private HomePresenter presenter;

    public HomeRepositoryImpl(HomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDataInstitutionByPartner(RequestUser requestUser) {
        Call<ArrayList<InstitutionByPartner>> call = ApiClient.getInstance().getApi().getInstitutions("Token " + requestUser.getToken());
        call.enqueue(new Callback<ArrayList<InstitutionByPartner>>() {
            @Override
            public void onResponse(Call<ArrayList<InstitutionByPartner>> call, Response<ArrayList<InstitutionByPartner>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getInstitutionByPartnerSuccess(response.body());
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
            public void onFailure(Call<ArrayList<InstitutionByPartner>> call, Throwable t) {
                presenter.responseError("Error1");
            }

        });
    }

    @Override
    public void getDataUser(RequestUser requestUser) {
        Call<DataUser> call = ApiClient.getInstance().getApi().getDataUser("Token " + requestUser.getToken());
        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getDataUserSuccess(response.body());
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
            public void onFailure(Call<DataUser> call, Throwable t) {
                presenter.responseError("error");
            }

        });
    }

    @Override
    public void getModalities() {
        Call<ArrayList<Modality>> call = ApiClient.getInstance().getApi().getModality("Token " + Utils.getInstance().getObj().getToken());
        call.enqueue(new Callback<ArrayList<Modality>>() {
            @Override
            public void onResponse(Call<ArrayList<Modality>> call, Response<ArrayList<Modality>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getModalitySuccess(response.body());
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
            public void onFailure(Call<ArrayList<Modality>> call, Throwable t) {
                presenter.responseError("error");
            }
        });

    }

    @Override
    public void setLogout() {
        Call<UserLog> call = ApiClient.getInstance().getApi().login();
        call.enqueue(new Callback<UserLog>() {
            @Override
            public void onResponse(Call<UserLog> call, Response<UserLog> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getLogoutSuccess();
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
            public void onFailure(Call<UserLog> call, Throwable t) {
                presenter.responseError("error");
            }
        });
    }
}
