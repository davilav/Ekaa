package com.pma.ekaa.data.repository.school;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.Result;
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
    public void getListBeneficiary(String keyword, int page) {
        retrofit2.Call<BeneficiaryArray> call = ApiClient.getInstance().getApi().listStudents("Token "+ Utils.getInstance().getDataUser().getToken(), keyword, page);
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

    @Override
    public void getAttendanceToday(int userID) {
        retrofit2.Call<ArrayList<AttendanceToday>> call = ApiClient.getInstance().getApi().getTodayAttendance(String.valueOf(userID), "Token "+ Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<AttendanceToday>>() {
            @Override
            public void onResponse(Call<ArrayList<AttendanceToday>> call, Response<ArrayList<AttendanceToday>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.attendanceTodaySuccess(response.body());
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
            public void onFailure(Call<ArrayList<AttendanceToday>> call, Throwable t) {
                presenter.responseError("Error1");
            }
        });
    }

    @Override
    public void setRegisterAttendance(Attendance attendance) {

    }

    @Override
    public void setCreateBeneficiary(RegisterBeneficiary registerBeneficiary) {

        Call<Result> call = ApiClient.getInstance().getApi().registerStudents(registerBeneficiary,"Token " + Utils.getInstance().getDataUser().getToken());
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
        Call<Result> call = ApiClient.getInstance().getApi().updateStudent(id,registerBeneficiary, "Token " + Utils.getInstance().getDataUser().getToken());
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
