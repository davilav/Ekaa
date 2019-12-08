package com.pma.ekaa.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.RegisterStudent;
import com.pma.ekaa.ui.register.RegisterActivity;
import com.pma.ekaa.ui.student.create_edit_student.CreateEditStudentFragment;
import com.pma.ekaa.ui.student.presenter.StudentPresenter;
import com.pma.ekaa.ui.student.presenter.StudentPresenterImpl;
import com.pma.ekaa.ui.student.show_student.ShowStudentFragment;

import es.dmoral.toasty.Toasty;

public class StudentActivity extends AppCompatActivity implements StudentView, CreateEditStudentFragment.OnFragmentInteractionListener, ShowStudentFragment.OnFragmentInteractionListener {

    public static String SELECTED_ITEM = "select_item";
    public static String OBJECT_BENEFICIARIES = "object_beneficiaries";
    public static String INSTITUTION_ID = "institution_id";
    public static String GROUP_ID = "group_id";

    public final static int SHOW = 0;
    public final static int CREATE = 1;
    public final static int EDIT = 2;

    private ConstraintLayout loading;
    private FrameLayout container;
    private String registerStudent;
    private int institutionID;
    private int groupID;
    private int selectItem;
    private String objectBeneficiary;
    private StudentPresenter presenter;
    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        if(savedInstanceState != null) {
            selectItem = savedInstanceState.getInt(SELECTED_ITEM);
            objectBeneficiary = savedInstanceState.getString(OBJECT_BENEFICIARIES);
            institutionID = savedInstanceState.getInt(INSTITUTION_ID);
            groupID = savedInstanceState.getInt(GROUP_ID);
        } else {
            selectItem = getIntent().getIntExtra(SELECTED_ITEM, -1);
            objectBeneficiary = getIntent().getStringExtra(OBJECT_BENEFICIARIES);
            institutionID = getIntent().getIntExtra(INSTITUTION_ID, -1);
            groupID = getIntent().getIntExtra(GROUP_ID, -1);
        }

        presenter = new StudentPresenterImpl(this);
        container = findViewById(R.id.containerStudent);
        loading = findViewById(R.id.progressBar);

        registerStudent = new Gson().toJson(
                new RegisterStudent(null, institutionID, groupID, 1, null)
        );

        selectAction();
    }

    private void selectAction() {
        switch (selectItem) {
            case SHOW:
                currentFragment = ShowStudentFragment.newInstance(selectItem, objectBeneficiary);
                break;
            case CREATE:
            case EDIT:
                currentFragment = CreateEditStudentFragment.newInstance(selectItem, objectBeneficiary, registerStudent);
                break;
        }
        replaceFragment();
    }

    private void replaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerStudent, currentFragment)
                .commit();
    }

    @Override
    public void editStudent() {
        currentFragment = CreateEditStudentFragment.newInstance(EDIT, objectBeneficiary, registerStudent);
        replaceFragment();
    }

    @Override
    public void setUploadBeneficiary(RegisterStudent registerStudent, int selectItem) {
        showLoading();
        presenter.setUploadStudent(registerStudent, selectItem);
    }


    @Override
    public void createStudentSuccess() {
        hideLoading();
        Toasty.success(this, "Estudiante creado con exito", Toast.LENGTH_SHORT, true).show();
        finish();
    }

    @Override
    public void updateStudentSuccess() {
        hideLoading();
        Toasty.success(this, "Estudiante actualizado con exito", Toast.LENGTH_SHORT, true).show();
        finish();
    }

    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show();
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
