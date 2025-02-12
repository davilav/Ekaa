package com.pma.ekaa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.school.SchoolActivity;
import com.pma.ekaa.ui.settings.SettingsActivity;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.home.presenter.HomePresenter;
import com.pma.ekaa.ui.home.presenter.HomePresenterImpl;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;
import com.pma.ekaa.utils.Utils;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends BaseActivity implements HomeView, PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    private ImageView kitchen;
    private ImageView school;
    private ImageView inkind;
    private ImageView walkers;
    private ImageView settings;
    private ImageView menu;
    private ConstraintLayout loading;
    private ConstraintLayout containerUbication;
    private ConstraintLayout containerHome;
    private EditText departmentGeolocation;
    private EditText townGeolocation;
    private EditText institutionGeolocation;
    private TextView splashtext;
    private TextView userText;
    private TextView emailtext;
    private Button bar;
    String fadein = "fadein-to-fadeout";

    private int partnerID = Utils.getInstance().getDataUser().getPartner();

    private String arrayDepartment = "";
    private String arrayTown = "";
    private String arrayInstitution = "";

    private int departmentID = 0;
    private int townID = 0;
    private Data institution;

    private ArrayList<Modality> arrayModality;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenterImpl(this);

        initViews();

        menu.setOnClickListener(this);
        kitchen.setOnClickListener(this);
        school.setOnClickListener(this);
        inkind.setOnClickListener(this);
        walkers.setOnClickListener(this);
        settings.setOnClickListener(this);
        bar.setOnClickListener(this);
        departmentGeolocation.setOnClickListener(this);
        townGeolocation.setOnClickListener(this);
        institutionGeolocation.setOnClickListener(this);

        showLoading();

        setDataUserSuccess();
        presenter.getDataDepartment(partnerID);
        presenter.getModalities();

    }

    public void initViews() {

        loading = findViewById(R.id.progressBar);
        containerHome = findViewById(R.id.containerHome);
        containerUbication = findViewById(R.id.containerUbication);

        splashtext = findViewById(R.id.splashUser);
        menu = findViewById(R.id.menupointbutton);
        kitchen = findViewById(R.id.kitchenButton);
        school = findViewById(R.id.schoolButton);
        inkind = findViewById(R.id.inkindButton);
        walkers = findViewById(R.id.walkersButton);
        departmentGeolocation = findViewById(R.id.departmentGeolocation);
        townGeolocation = findViewById(R.id.townGeolocation);
        institutionGeolocation = findViewById(R.id.institutionGeolocation);
        settings = findViewById(R.id.settingsButton);
        userText = findViewById(R.id.userText);
        emailtext = findViewById(R.id.textemailhome);
        bar = findViewById(R.id.btn_recovery);
        townGeolocation.setEnabled(false);
        institutionGeolocation.setEnabled(false);
        bar.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menupointbutton:
                showPopup(view);
                break;
            case R.id.schoolButton:
                Intent intentSchool = new Intent(HomeActivity.this, SchoolActivity.class);
                intentSchool.putExtra(NotSchoolActivity.OPTION_MODALITY, getModality(3));
                intentSchool.putExtra(SchoolActivity.INSTITUTION_ID, institution.getId());
                startActivity(intentSchool);
                customType(HomeActivity.this, fadein);
                break;
            case R.id.kitchenButton:
                Intent intent = new Intent(HomeActivity.this, NotSchoolActivity.class);
                intent.putExtra(NotSchoolActivity.OPTION_ACTION, NotSchoolActivity.KITCHEN);
                intent.putExtra(NotSchoolActivity.OPTION_MODALITY, getModality(NotSchoolActivity.KITCHEN));
                intent.putExtra(NotSchoolActivity.INSTITUTION_ID, institution.getId());
                startActivity(intent);
                customType(HomeActivity.this, fadein);
                break;
            case R.id.walkersButton:
                Intent intentWalkers = new Intent(HomeActivity.this, NotSchoolActivity.class);
                intentWalkers.putExtra(NotSchoolActivity.OPTION_ACTION, NotSchoolActivity.WALKERS);
                intentWalkers.putExtra(NotSchoolActivity.OPTION_MODALITY, getModality(NotSchoolActivity.WALKERS));
                intentWalkers.putExtra(NotSchoolActivity.INSTITUTION_ID, institution.getId());
                startActivity(intentWalkers);
                customType(HomeActivity.this, fadein);
                break;
            case R.id.inkindButton:
                Intent intentInkind = new Intent(HomeActivity.this, NotSchoolActivity.class);
                intentInkind.putExtra(NotSchoolActivity.OPTION_ACTION, NotSchoolActivity.INKIND);
                intentInkind.putExtra(NotSchoolActivity.OPTION_MODALITY, getModality(NotSchoolActivity.INKIND));
                intentInkind.putExtra(NotSchoolActivity.INSTITUTION_ID, institution.getId());
                startActivity(intentInkind);
                customType(HomeActivity.this, fadein);
                break;
            case R.id.settingsButton:
                Intent intentSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                customType(HomeActivity.this, fadein);
                break;
            case R.id.btn_recovery:
                showLoading();
                containerUbication.setVisibility(View.GONE);
                goHome();
                break;
            case R.id.departmentGeolocation:
                if(!arrayDepartment.equals("")) {
                    SelectOptionDialog.newInstance(
                            arrayDepartment,
                            new SelectOptionDialog.onListenerInterface() {
                                @Override
                                public void optionSelect(Data data) {
                                    departmentID = data.getId();
                                    departmentGeolocation.setText(data.getName());
                                    townGeolocation.setText(getResources().getString(R.string.selectopion));
                                    townGeolocation.setEnabled(true);
                                    institutionGeolocation.setText(getResources().getString(R.string.selectopion));
                                    institutionGeolocation.setEnabled(false);
                                    bar.setEnabled(false);
                                    showLoading();
                                    presenter.getDataTown(partnerID,departmentID);
                                }
                            }).show(getSupportFragmentManager(), "");
                } else {
                    Toasty.warning(HomeActivity.this, getResources().getString(R.string.departamentosfound), Toast.LENGTH_SHORT, true).show();
                }
                break;
            case R.id.townGeolocation:
                if(!arrayTown.equals("")) {
                    SelectOptionDialog.newInstance(
                            arrayTown,
                            new SelectOptionDialog.onListenerInterface() {
                                @Override
                                public void optionSelect(Data data) {
                                    townID = data.getId();
                                    townGeolocation.setText(data.getName());
                                    institutionGeolocation.setText(getResources().getString(R.string.selectopion));
                                    institutionGeolocation.setEnabled(true);
                                    bar.setEnabled(false);
                                    showLoading();
                                    presenter.getDataInstitution(townID);
                                }
                            }).show(getSupportFragmentManager(), "");
                } else {
                    Toasty.warning(HomeActivity.this, getResources().getString(R.string.townsfound), Toast.LENGTH_SHORT, true).show();
                }
                break;
            case R.id.institutionGeolocation:
                if(!arrayInstitution.equals("")) {
                    SelectOptionDialog.newInstance(
                            arrayInstitution,
                            new SelectOptionDialog.onListenerInterface() {
                                @Override
                                public void optionSelect(Data data) {
                                    institution = data;
                                    institutionGeolocation.setText(data.getName());
                                    bar.setEnabled(true);
                                }
                            }).show(getSupportFragmentManager(), "");
                    break;
                } else {
                    Toasty.warning(HomeActivity.this,getResources().getString(R.string.institutionsfound), Toast.LENGTH_SHORT, true).show();
                }
                break;
            default:
                break;
        }
    }

    private String getModality(int option) {

        ArrayList<Modality> filterModality = new ArrayList<>();
        int selector;

        switch (option) {
            case NotSchoolActivity.KITCHEN:
                selector = 1;
                break;
            case NotSchoolActivity.WALKERS:
                selector = 2;
                break;
            case NotSchoolActivity.INKIND:
                selector = 4;
                break;
            default:
                selector = 3;
                break;
        }


        for(int cont = 0; cont < arrayModality.size(); cont++) {
            if(arrayModality.get(cont).getModalityType() == selector) {
                filterModality.add(arrayModality.get(cont));
            }
        }

        return new Gson().toJson(filterModality);

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
    public void getDepartmentSuccess(ArrayList<Data> data) {
        if(data.size() != 0){
            arrayDepartment = new Gson().toJson(data);
        }
        hideLoading();
    }

    private void setDataUserSuccess() {
        splashtext.setText(Utils.getInstance().getDataUser().getUsername());
        userText.setText(Utils.getInstance().getDataUser().getUsername());
        emailtext.setText(Utils.getInstance().getDataUser().getEmail());
    }

    @Override
    public void getTownSuccess(ArrayList<Data> data) {
        if(data.size() != 0){
            arrayTown = new Gson().toJson(data);
        }
        hideLoading();
    }

    @Override
    public void getInstitutionSuccess(ArrayList<Data> data) {
        if(data.size() != 0){
            arrayInstitution = new Gson().toJson(data);
        }
        hideLoading();
    }

    @Override
    public void getLogoutSuccess() {
        Toasty.success(HomeActivity.this, getResources().getString(R.string.adios), Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
        startActivity(intent);
        customType(HomeActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void getModalitySuccess(ArrayList<Modality> modality) {
        arrayModality = modality;
    }

    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.warning(HomeActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }

    public void goHome() {


        containerHome.setVisibility(View.VISIBLE);

        switch (institution.getModalityType()){
            case 1:
                walkers.setImageAlpha(120);
                school.setImageAlpha(120);
                inkind.setImageAlpha(120);
                walkers.setClickable(false);
                school.setClickable(false);
                inkind.setClickable(false);
                break;
            case 2:
                kitchen.setImageAlpha(120);
                school.setImageAlpha(120);
                inkind.setImageAlpha(120);
                kitchen.setClickable(false);
                school.setClickable(false);
                inkind.setClickable(false);
                break;
            case 3:
                kitchen.setImageAlpha(120);
                walkers.setImageAlpha(120);
                inkind.setImageAlpha(120);
                kitchen.setClickable(false);
                walkers.setClickable(false);
                inkind.setClickable(false);
                break;
            default:
                kitchen.setImageAlpha(120);
                walkers.setImageAlpha(120);
                school.setImageAlpha(120);
                kitchen.setClickable(false);
                walkers.setClickable(false);
                school.setClickable(false);
                break;
        }

        hideLoading();
    }

    private void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.activity_home_drawer);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
                customType(HomeActivity.this, "fadein-to-fadeout");
                return true;
            case R.id.item2:
            case R.id.item3:
                Toasty.info(HomeActivity.this, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
                return true;
            case R.id.item4:
                presenter.setLogout();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
