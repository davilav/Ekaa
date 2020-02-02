package com.pma.ekaa.data.repository.school;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.RegisterStudent;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.school.presenter.SchoolPresenter;
import com.pma.ekaa.utils.Utils;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolRepositoryImpl implements SchoolRepository {

    private SchoolPresenter presenter;
    String token = "Token ";
    String error = "Error ";
    String error1 = "Error 1";

    public SchoolRepositoryImpl(SchoolPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void getListBeneficiary(String keyword, int page, int idSchool, int idGroup) {
        retrofit2.Call<BeneficiaryArray> call = ApiClient.getInstance().getApi().listStudents( token+ Utils.getInstance().getDataUser().getToken(), String.valueOf(idSchool), keyword, page);
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
        retrofit2.Call<ArrayList<AttendanceToday>> call = ApiClient.getInstance().getApi().getTodayAttendance(String.valueOf(userID), token+ Utils.getInstance().getDataUser().getToken());
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
        Call<Attendance> call = ApiClient.getInstance().getApi().registerAttendance(attendance,(token +Utils.getInstance().getDataUser().getToken()));
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

    @Override
    public void setCreateBeneficiary(RegisterStudent registerStudent) {

        Call<Result> call = ApiClient.getInstance().getApi().registerStudents(registerStudent, token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        presenter.createBeneficiarySuccess();
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
            public void onFailure(Call<Result> call, Throwable t) {
                presenter.responseError(error);
            }

        });

    }

    @Override
    public void setUpdateBeneficiary(String id, RegisterStudent registerStudent) {
        Call<Result> call = ApiClient.getInstance().getApi().updateStudent(id, registerStudent, token + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case 200:
                        presenter.updateBeneficiarySuccess();
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
            public void onFailure(Call<Result> call, Throwable t) {
                presenter.responseError(error);
            }

        });

    }

}
