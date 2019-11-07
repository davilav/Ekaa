package com.pma.ekaa.data.repository.not_school;

import android.view.View;
import android.widget.Toast;

import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.adapters.ItemAdapter;
import com.pma.ekaa.ui.not_school.presenter.NotSchoolPresenter;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotSchoolRepositoryImpl implements NotSchoolRepository {

    private NotSchoolPresenter presenter;

    public NotSchoolRepositoryImpl(NotSchoolPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getListBeneficiary(String token, String keyword, int page) {
        retrofit2.Call<BeneficiaryArray> call = ApiClient.getInstance().getApi().listBeneficiary("Token "+ token, keyword, page);
        call.enqueue(new Callback<BeneficiaryArray>() {
            @Override
            public void onResponse(Call<BeneficiaryArray> call, Response<BeneficiaryArray> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getListBeneficiarySuccess(response.body());
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
            public void onFailure(Call<BeneficiaryArray> call, Throwable t) {
                presenter.responseError("Error1");
            }
        });
    }
}
