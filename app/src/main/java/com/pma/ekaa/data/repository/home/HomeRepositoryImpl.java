package com.pma.ekaa.data.repository.home;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.UserLog;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.home.presenter.HomePresenter;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepositoryImpl implements HomeRepository {

    private HomePresenter presenter;
    String token = "Token ";
    String error = "Error ";
    String error1 = "Error 1";

    public HomeRepositoryImpl(HomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDataDepartment(int partnerID) {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getDepartments(Integer.toString(partnerID),token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getDepartmentSuccess(response.body());
                        break;
                    case 400:
                        presenter.responseError(error);
                        break;
                    default:
                        presenter.responseError(error1);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.responseError(error1);
            }

        });
    }

    @Override
    public void getDataTown(int partnerID, int departmentID) {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getTown(Integer.toString(partnerID),Integer.toString(departmentID), token  + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getTownSuccess(response.body());
                        break;
                    case 400:
                        presenter.responseError(error);
                        break;
                    default:
                        presenter.responseError(error1);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.responseError(error1);
            }

        });
    }

    @Override
    public void getDataInstitution(int townID) {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getInstitution(String.valueOf(Utils.getInstance().getDataUser().getPartner()), Integer.toString(townID), token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getInstitutionSuccess(response.body());
                        break;
                    case 400:
                        presenter.responseError(error);
                        break;
                    default:
                        presenter.responseError(error1);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.responseError(error1);
            }

        });
    }

    @Override
    public void getModalities() {
        Call<ArrayList<Modality>> call = ApiClient.getInstance().getApi().getModality(token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<Modality>>() {
            @Override
            public void onResponse(Call<ArrayList<Modality>> call, Response<ArrayList<Modality>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getModalitySuccess(response.body());
                        break;
                    case 400:
                        presenter.responseError(error);
                        break;
                    default:
                        presenter.responseError(error1);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Modality>> call, Throwable t) {
                presenter.responseError(error);
            }
        });

    }

    @Override
    public void setLogout() {
        Call<UserLog> call = ApiClient.getInstance().getApi().logout(token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<UserLog>() {
            @Override
            public void onResponse(Call<UserLog> call, Response<UserLog> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getLogoutSuccess();
                        break;
                    case 400:
                        presenter.responseError(error);
                        break;
                    default:
                        presenter.responseError(error1);
                        break;
                }
            }

            @Override
            public void onFailure(Call<UserLog> call, Throwable t) {
                presenter.responseError(error);
            }
        });
    }
}
