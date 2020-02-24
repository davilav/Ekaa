package com.pma.ekaa.data.repository.beneficiary;

import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.beneficiary.presenter.BeneficiaryPresenter;
import com.pma.ekaa.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BeneficiaryRepositoryImpl implements BeneficiaryRepository {

    private BeneficiaryPresenter presenter;


    public BeneficiaryRepositoryImpl(BeneficiaryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setCreateBeneficiary(RegisterBeneficiary registerBeneficiary) {
        Call<Result> call = ApiClient.getInstance().getApi().registerBeneficiary(registerBeneficiary,"Token " + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        presenter.createBeneficiarySuccess();
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
            public void onFailure(Call<Result> call, Throwable t) {
                presenter.responseError("error");
            }

        });
    }

    @Override
    public void setUpdateBeneficiary(String id, RegisterBeneficiary registerBeneficiary) {
        Call<Result> call = ApiClient.getInstance().getApi().updateBeneficiary(id,registerBeneficiary, "Token " + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case 200:
                        presenter.updateBeneficiarySuccess();
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
            public void onFailure(Call<Result> call, Throwable t) {
                presenter.responseError("error");
            }

        });
    }
}
