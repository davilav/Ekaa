package com.pma.ekaa.data.repository.school;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.home.presenter.HomePresenter;
import com.pma.ekaa.ui.school.presenter.SchoolPresenter;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolRepositoryImpl implements SchoolRepository {

    private SchoolPresenter presenter;

    public SchoolRepositoryImpl(SchoolPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void getDataGroup() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getGroups("Token " + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getGroupSuccess(response.body());
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

            }
        });
    }

    @Override
    public void getDataClass() {

    }
}
