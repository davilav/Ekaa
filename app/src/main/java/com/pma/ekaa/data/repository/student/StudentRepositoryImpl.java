package com.pma.ekaa.data.repository.student;

import com.pma.ekaa.data.models.RegisterStudent;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.student.presenter.StudentPresenter;
import com.pma.ekaa.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRepositoryImpl implements StudentRepository {

    private StudentPresenter presenter;


    public StudentRepositoryImpl(StudentPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setCreateStudent(RegisterStudent registerStudent) {
        Call<Result> call = ApiClient.getInstance().getApi().registerStudents(registerStudent,"Token " + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        presenter.createStudentSuccess();
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
    public void setUpdateStudent(RegisterStudent registerStudent) {
        Call<Result> call = ApiClient.getInstance().getApi().updateStudent(String.valueOf(registerStudent.getBeneficiary().getId()), registerStudent, "Token " + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        presenter.updateStudentSuccess();
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
