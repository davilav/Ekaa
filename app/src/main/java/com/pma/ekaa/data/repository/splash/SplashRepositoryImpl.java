package com.pma.ekaa.data.repository.splash;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.splash.presenter.SplashPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashRepositoryImpl implements SplashRepository {

    private SplashPresenter presenter;
    String error = "ERROR";

    public SplashRepositoryImpl(SplashPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getData() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getNationality();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getNationalitySuccess(response.body());
                } else {
                    presenter.loginError(error);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }

    @Override
    public void getDocumentsType() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getDocuments();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getDocumentsTypeSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }

    @Override
    public void getGenders() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getGenders();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getGendersSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }

    @Override
    public void getMaritalStatus() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getMaritalStatus();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getMaritalStatusSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }

    @Override
    public void getMigratoryStatus() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getMigratoryStatus();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getMigratorySuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }

    @Override
    public void getRecipient() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getRecipients();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getRecipientSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }

    @Override
    public void getHouseHoldRole() {

        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().gethouseholdRoles();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getHouseHoldRoleSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });

    }

    @Override
    public void getDisabilities() {

        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getDisabilities();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getDisabilitiesSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });

    }

    @Override
    public void getPrograms() {

        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getPrograms();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getProgramsSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });

    }

    @Override
    public void getGroups() {
        Call<ArrayList<Data>> call = ApiClient.getInstance().getApi().getGroups();
        call.enqueue(new Callback<ArrayList<Data>>() {
            @Override
            public void onResponse(Call<ArrayList<Data>> call, Response<ArrayList<Data>> response) {
                if(response.isSuccessful()){
                    presenter.getGroupsSuccess(response.body());
                } else {
                    presenter.loginError(error);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Data>> call, Throwable t) {
                presenter.loginError(error);
            }
        });
    }
}
