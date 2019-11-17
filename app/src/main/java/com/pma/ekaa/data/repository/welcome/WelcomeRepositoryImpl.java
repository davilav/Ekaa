package com.pma.ekaa.data.repository.welcome;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.welcome.presenter.WelcomePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeRepositoryImpl implements WelcomeRepository {

    private WelcomePresenter presenter;

    public WelcomeRepositoryImpl(WelcomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPartnersData() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getPartners();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getPartnersSuccess(response.body());
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
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.responseError("Error1");
            }

        });
    }
}
