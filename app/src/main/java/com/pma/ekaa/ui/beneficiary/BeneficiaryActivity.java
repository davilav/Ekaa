package com.pma.ekaa.ui.beneficiary;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.beneficiary.presenter.BeneficiaryPresenter;
import com.pma.ekaa.ui.beneficiary.presenter.BeneficiaryPresenterImpl;
import com.pma.ekaa.ui.beneficiary.show_beneficiary.ShowBeneficiaryFragment;
import com.pma.ekaa.ui.beneficiary.create_edit_beneficiary.CreateEditBeneficiaryFragment;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;

import es.dmoral.toasty.Toasty;

public class BeneficiaryActivity extends BaseActivity implements CreateEditBeneficiaryFragment.OnFragmentInteractionListener, ShowBeneficiaryFragment.OnFragmentInteractionListener, BeneficiaryView {

    private BeneficiaryPresenter presenter;

    public static final String SELECTED_ITEM = "select_item";
    public static final String OBJECT_BENEFICIARIES = "object_beneficiaries";
    public static final String OPTION_AGREEMENT = "object_agreement";

    public static final int SHOW = 0;
    public static final int CREATE = 1;
    public static final int EDIT = 2;

    private ConstraintLayout loading;
    private int selectItem;
    private int optionAction;
    private String objectBeneficiary;
    private String optionAgreement = "";
    private boolean isHeadFamilyBeneficiary = false;
    private String familyCode = null;

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

        loading = findViewById(R.id.progressBar);
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
                currentFragment = CreateEditBeneficiaryFragment.newInstance(selectItem, optionAction, optionAgreement, objectBeneficiary, familyCode);
                break;
            default:
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
        currentFragment = CreateEditBeneficiaryFragment.newInstance(EDIT, optionAction, optionAgreement, objectBeneficiary, familyCode);
        replaceFragment();
    }

    @Override
    public void setUploadBeneficiary(String id, RegisterBeneficiary registerBeneficiary, int selectItem, boolean isHeadFamily) {
        showLoading();
        if(isHeadFamily) {
            familyCode = registerBeneficiary.getDocument();
            isHeadFamilyBeneficiary = true;
        }
        presenter.setUploadBeneficiary(id, registerBeneficiary, selectItem);
    }

    @Override
    public void addMemberFamily(String agreement, String familyCode) {
        currentFragment = CreateEditBeneficiaryFragment.newInstance(CREATE, optionAction, agreement, null, familyCode);
        replaceFragment();
    }

    @Override
    public void createBeneficiarySuccess() {
        hideLoading();
        if(isHeadFamilyBeneficiary){
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.usersuccess))
                    .setMessage(getResources().getString(R.string.useradd))
                    .setCancelable(false)
                    .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton(getResources().getString(R.string.agree), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            currentFragment = CreateEditBeneficiaryFragment.newInstance(selectItem, optionAction, optionAgreement, objectBeneficiary, familyCode);
                            replaceFragment();
                        }
                    }).show();
        } else {
            Toasty.success(this, getResources().getString(R.string.beneficiarysucces), Toast.LENGTH_SHORT, true).show();
            finish();
        }
    }

    @Override
    public void updateBeneficiarySuccess() {
        hideLoading();
        Toasty.success(this, getResources().getString(R.string.beneficiaryupdate), Toast.LENGTH_SHORT, true).show();
        finish();
    }

    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.warning(this, "Error", Toast.LENGTH_SHORT, true).show();
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
