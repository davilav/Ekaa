package com.pma.ekaa.data.repository.not_school;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.not_school.presenter.NotSchoolPresenter;
import com.pma.ekaa.utils.Utils;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotSchoolRepositoryImpl implements NotSchoolRepository {

    private NotSchoolPresenter presenter;
    String token = "Token ";
    String error = "Error ";
    String error1 = "Error1 ";

    public NotSchoolRepositoryImpl(NotSchoolPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getListBeneficiary(String keyword, int page) {
        retrofit2.Call<BeneficiaryArray> call = ApiClient.getInstance().getApi().listBeneficiary(token+ Utils.getInstance().getDataUser().getToken(), keyword, page);
        call.enqueue(new Callback<BeneficiaryArray>() {
            @Override
            public void onResponse(Call<BeneficiaryArray> call, Response<BeneficiaryArray> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getListBeneficiarySuccess(response.body());
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
            public void onFailure(Call<BeneficiaryArray> call, Throwable t) {
                presenter.responseError(error1);
            }
        });
    }

    @Override
    public void getAttendanceToday(int userID) {
        retrofit2.Call<ArrayList<AttendanceToday>> call = ApiClient.getInstance().getApi().getTodayAttendance(String.valueOf(userID), token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<AttendanceToday>>() {
            @Override
            public void onResponse(Call<ArrayList<AttendanceToday>> call, Response<ArrayList<AttendanceToday>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.attendanceTodaySuccess(response.body());
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
            public void onFailure(Call<ArrayList<AttendanceToday>> call, Throwable t) {
                presenter.responseError(error1);
            }
        });
    }

    @Override
    public void setRegisterAttendance(Attendance attendance) {
        Call<Attendance> call = ApiClient.getInstance().getApi().registerAttendance(attendance,(token+Utils.getInstance().getDataUser().getToken()));
        call.enqueue(new Callback<Attendance>() {
            @Override
            public void onResponse(Call<Attendance> call, Response<Attendance> response) {

                switch (response.code()) {
                    case 200:
                    case 201:
                        presenter.setRegisterAttendanceSuccess();
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
            public void onFailure(Call<Attendance> call, Throwable t) {
                presenter.responseError(error1);
            }
        });

    }
}
