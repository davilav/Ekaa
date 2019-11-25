package com.pma.ekaa.ui.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenter;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenterImpl;

public class AttendanceDetailActivity extends AppCompatActivity implements AttendanceView {

    public static String USER_ID = "user_id";

    private AttendancePresenter presenter;
    private int userID;
    private ConstraintLayout loading;
    private TextView nameUser;
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

        presenter = new AttendancePresenterImpl(this);

        presenter.getAttendanceUser(userID);

    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }
}
