package com.pma.ekaa.ui.school;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.school.presenter.SchoolPresenter;
import com.pma.ekaa.ui.school.presenter.SchoolPresenterImpl;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;

public class SchoolActivity extends BaseActivity implements SchoolView, View.OnClickListener{

    private LinearLayout schoolSplash;
    private ConstraintLayout students, loading;
    private Animation fromBottom;
    private EditText group;
    private ButtonProgressBar bar;

    private int groupID = 0;
    private String arrayGroup = "";
    private ArrayList<Data> arrayData;

    private SchoolPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        presenter = new SchoolPresenterImpl(this);

        initViews();

        group.setOnClickListener(this);
        bar.setOnClickListener(this);

        showLoading();

        presenter.getDataGroup();
        presenter.getDataClass();

    }


    public void goToStudentData() {
        schoolSplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        students.setVisibility(View.VISIBLE);
        students.startAnimation(fromBottom);
    }


    public void initViews(){

        loading = findViewById(R.id.progressBar);
        schoolSplash = findViewById(R.id.textsplash);
        students = findViewById(R.id.students);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.fromdown);
        group = findViewById(R.id.groupselect);
        bar = findViewById(R.id.btn_recovery);
        bar.setEnabled(false);


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
    public void getGroupSuccess(ArrayList<Data> data) {
        if(data.size() != 0){
            arrayGroup = new Gson().toJson(data);
        }
        hideLoading();
    }

    @Override
    public void getClassSuccess(ArrayList<Data> data) {

    }

    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.warning(SchoolActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.groupselect:
                if(!arrayGroup.equals("")) {
                    SelectOptionDialog.newInstance(
                            arrayGroup,
                            new SelectOptionDialog.onListenerInterface() {
                                @Override
                                public void optionSelect(Data data) {
                                    groupID = data.getId();
                                    group.setText(data.getName());
                                    bar.setEnabled(true);
                                }
                            }).show(getSupportFragmentManager(), "");
                } else {
                    Toasty.warning(SchoolActivity.this, "No se encontraron grupos", Toast.LENGTH_SHORT, true).show();
                }
                break;

            case R.id.btn_recovery:
                goToStudentData();
                break;
        }
    }
}
