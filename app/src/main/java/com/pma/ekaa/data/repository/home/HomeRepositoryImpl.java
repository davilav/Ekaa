package com.pma.ekaa.data.repository.home;

import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.home.presenter.HomePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepositoryImpl implements HomeRepository {

    private HomePresenter presenter;

    public HomeRepositoryImpl(HomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDataInstitutionByPartner(RequestUser requestUser) {
        retrofit2.Call<ArrayList<InstitutionByPartner>> call = ApiClient.getInstance().getApi().getInstitutions("Token " + requestUser.getToken());
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
}
