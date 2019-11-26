package com.pma.ekaa.ui.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.AttendanceDetail;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenter;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenterImpl;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;

public class AttendanceDetailActivity extends AppCompatActivity implements AttendanceView {

    public static String USER_ID = "user_id";

    private AttendancePresenter presenter;
    private int userID;
    private ConstraintLayout loading;
    private TextView nameUser, textNotFound;
    private RecyclerView recyclerAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_detail);
        if(savedInstanceState != null){
            userID = getIntent().getIntExtra(USER_ID, -1);
        }
        nameUser = findViewById(R.id.tv_name);
        recyclerAttendance = findViewById(R.id.recycler_attendance);
        textNotFound = findViewById(R.id.textNotFound);
        presenter = new AttendancePresenterImpl(this);

        String name= Utils.getInstance().getDataUser().getFirstName() + " " + Utils.getInstance().getDataUser().getLastName();
        nameUser.setText(name);

        showLoading();
        presenter.getAttendanceUser(userID);
    }

    @Override
    public void getAttendanceUserSuccess(ArrayList<AttendanceDetail> data) {
        if(data.size() == 0){
            recyclerAttendance.setVisibility(View.GONE);
            textNotFound.setVisibility(View.VISIBLE);

        } else {
            recyclerAttendance.setVisibility(View.VISIBLE);
            textNotFound.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void responseError(String msg) {
        hideLoading();
    }
}
