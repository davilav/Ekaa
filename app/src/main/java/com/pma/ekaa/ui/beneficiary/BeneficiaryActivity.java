package com.pma.ekaa.ui.beneficiary;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.beneficiary.presenter.BeneficiaryPresenter;
import com.pma.ekaa.ui.beneficiary.presenter.BeneficiaryPresenterImpl;
import com.pma.ekaa.ui.beneficiary.show_beneficiary.ShowBeneficiaryFragment;
import com.pma.ekaa.ui.beneficiary.create_edit_beneficiary.CreateEditBeneficiaryFragment;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;

import es.dmoral.toasty.Toasty;

public class BeneficiaryActivity extends BaseActivity implements CreateEditBeneficiaryFragment.OnFragmentInteractionListener, ShowBeneficiaryFragment.OnFragmentInteractionListener, BeneficiaryView {

    private BeneficiaryPresenter presenter;

    public static String SELECTED_ITEM = "select_item";
    public static String OBJECT_BENEFICIARIES = "object_beneficiaries";
    public static String OPTION_AGREEMENT = "object_agreement";

    public final static int SHOW = 0;
    public final static int CREATE = 1;
    public final static int EDIT = 2;


    private int selectItem;
    private int optionAction;
    private String objectBeneficiary;
    private String optionAgreement = "";

    private FrameLayout container;

    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);
        if(savedInstanceState != null) {
            selectItem = savedInstanceState.getInt(SELECTED_ITEM);
            optionAction = savedInstanceState.getInt(NotSchoolActivity.OPTION_ACTION);
            objectBeneficiary = savedInstanceState.getString(OBJECT_BENEFICIARIES);
            optionAgreement = savedInstanceState.getString(OPTION_AGREEMENT);
        } else {
            selectItem = getIntent().getIntExtra(SELECTED_ITEM, -1);
            optionAction = getIntent().getIntExtra(NotSchoolActivity.OPTION_ACTION, -1);
            objectBeneficiary = getIntent().getStringExtra(OBJECT_BENEFICIARIES);
            optionAgreement = getIntent().getStringExtra(OPTION_AGREEMENT);
        }

        container = findViewById(R.id.containerBeneficiary);
        presenter = new BeneficiaryPresenterImpl(this);
        selectAction();

    }

    private void selectAction() {
        switch (selectItem) {
            case SHOW:
                currentFragment = ShowBeneficiaryFragment.newInstance(selectItem, optionAction, objectBeneficiary);
                break;
            case CREATE:
            case EDIT:
                currentFragment = CreateEditBeneficiaryFragment.newInstance(selectItem, optionAction, optionAgreement, objectBeneficiary);
                break;
        }
        replaceFragment();
    }

    private void replaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerBeneficiary, currentFragment)
                .commit();
    }

    @Override
    public void editBeneficiary() {
        currentFragment = CreateEditBeneficiaryFragment.newInstance(EDIT, optionAction, optionAgreement, objectBeneficiary);
        replaceFragment();
    }

    @Override
    public void setUploadBeneficiary(String id, RegisterBeneficiary registerBeneficiary, int selectItem) {
        presenter.setUploadBeneficiary(id, registerBeneficiary, selectItem);
    }

    @Override
    public void createBeneficiarySuccess() {
        Toasty.success(this, "Usuario creado con exito", Toast.LENGTH_SHORT, true).show();
        finish();
    }

    @Override
    public void updateBeneficiarySuccess() {
        Toasty.success(this, "Usuario actualizado con exito", Toast.LENGTH_SHORT, true).show();
        finish();
    }

    @Override
    public void responseError(String msg) {
        Toasty.warning(this, "Adios!", Toast.LENGTH_SHORT, true).show();
    }
}
