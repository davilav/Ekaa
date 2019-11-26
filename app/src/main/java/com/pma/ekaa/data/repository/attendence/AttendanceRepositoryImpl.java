package com.pma.ekaa.data.repository.attendence;


import com.pma.ekaa.data.models.AttendanceDetail;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenter;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceRepositoryImpl implements AttendanceRepository {

    private AttendancePresenter presenter;

    public AttendanceRepositoryImpl(AttendancePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAttendanceUser(int userID) {
        Call<ArrayList<AttendanceDetail>> call = ApiClient.getInstance().getApi().getDetailAttendance(String.valueOf(userID), "Token " + Utils.getInstance().getDataUser().getToken());
        call.enqueue(new Callback<ArrayList<AttendanceDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<AttendanceDetail>> call, Response<ArrayList<AttendanceDetail>> response) {
                switch (response.code()) {
                    case 200:
                        presenter.getAttendanceUserSuccess(response.body());
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
            public void onFailure(Call<ArrayList<AttendanceDetail>> call, Throwable t) {
                presenter.responseError("Error1");
            }

        });
    }
}
