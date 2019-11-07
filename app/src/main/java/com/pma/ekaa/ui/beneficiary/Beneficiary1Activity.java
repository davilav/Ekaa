package com.pma.ekaa.ui.beneficiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.beneficiary.create_beneficiary.CreateBeneficiaryFragment;
import com.pma.ekaa.ui.beneficiary.show_edit_beneficiary.ShowEditBeneficiaryFragment;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;

public class Beneficiary1Activity extends BaseActivity implements ShowEditBeneficiaryFragment.OnFragmentInteractionListener, CreateBeneficiaryFragment.OnFragmentInteractionListener  {

    public static String SELECTED_ITEM = "select_item";

    public final static int SHOW = 0;
    public final static int CREATE = 1;
    public final static int EDIT = 2;


    private int selectItem;
    private int optionAction;

    private FrameLayout container;

    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary1);
        if(savedInstanceState != null) {
            selectItem = savedInstanceState.getInt(SELECTED_ITEM);
            optionAction = savedInstanceState.getInt(NotSchoolActivity.SELECTED_ITEM);
        } else {
            selectItem = getIntent().getIntExtra(SELECTED_ITEM, -1);
            optionAction = getIntent().getIntExtra(SELECTED_ITEM, -1);
        }

        container = findViewById(R.id.containerBeneficiary);

        selectAction();

    }

    private void selectAction() {
        switch (selectItem) {
            case SHOW:
                currentFragment = ShowEditBeneficiaryFragment.newInstance("1", "1");
                break;
            case CREATE:
                currentFragment = CreateBeneficiaryFragment.newInstance("1", "2");
                break;
            case EDIT:
                currentFragment = ShowEditBeneficiaryFragment.newInstance("1", "2");
                break;
        }
        replaceFragment();
    }

    private void replaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerBeneficiary, currentFragment)
                .commit();
    }

}
