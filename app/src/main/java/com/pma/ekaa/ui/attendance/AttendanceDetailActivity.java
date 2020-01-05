package com.pma.ekaa.ui.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.AttendanceDetail;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenter;
import com.pma.ekaa.ui.attendance.presenter.AttendancePresenterImpl;

import java.util.ArrayList;

public class AttendanceDetailActivity extends AppCompatActivity implements AttendanceView {

    public static String USER_DETAIL = "user_detail";

    private ConstraintLayout loading;
    private TextView textNotFound;
    private ImageView backButton;
    private RecyclerView recyclerAttendance;
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_detail);
        Result user;
        if(savedInstanceState != null){
            user = new Gson().fromJson(savedInstanceState.getString(USER_DETAIL), new TypeToken<Result>(){}.getType());
        } else {
            user = new Gson().fromJson(getIntent().getStringExtra(USER_DETAIL), new TypeToken<Result>(){}.getType());
        }

        loading = findViewById(R.id.progressBar);
        TextView nameUser = findViewById(R.id.tv_name);
        recyclerAttendance = findViewById(R.id.recycler_attendance);
        textNotFound = findViewById(R.id.textNotFound);
        AttendancePresenter presenter = new AttendancePresenterImpl(this);

        String name= user.getFirst_name() + " " + user.getSurname();
        nameUser.setText(name);

        showLoading();
        presenter.getAttendanceUser(user.getId());

    }

    @Override
    public void getAttendanceUserSuccess(ArrayList<AttendanceDetail> data) {
        hideLoading();
        if(data.size() == 0){
            recyclerAttendance.setVisibility(View.GONE);
            textNotFound.setVisibility(View.VISIBLE);

        } else {
            recyclerAttendance.setVisibility(View.VISIBLE);
            textNotFound.setVisibility(View.GONE);
            AttendanceAdapter mAdapter = new AttendanceAdapter(data);
            recyclerAttendance.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerAttendance.setAdapter(mAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerAttendance.getContext(), mLayoutManager.getOrientation());
            recyclerAttendance.addItemDecoration(dividerItemDecoration);

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
