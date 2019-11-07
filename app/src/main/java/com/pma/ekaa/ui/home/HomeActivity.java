package com.pma.ekaa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.school.SchoolActivity;
import com.pma.ekaa.ui.settings.SettingsActivity;
import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.home.presenter.HomePresenter;
import com.pma.ekaa.ui.home.presenter.HomePresenterImpl;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;
import com.pma.ekaa.utils.Utils;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends BaseActivity implements HomeView, PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    private ImageView kitchen, school, inkind, walkers, cloud, url, settings, info, menu, bgApp, clover;
    private String token = Utils.getInstance().getObj().getToken();

    private Animation bganim, cloveranim, fromtop, fromBottom;
    private LinearLayout textSplash, textHome, home;
    private Spinner partner, location;
    private TextView splashtext, userText, emailtext;
    private ButtonProgressBar bar;
    private int locationEmpty = 0;
    private ArrayList typesSpinner1 = new ArrayList();
    private ArrayList typesSpinner2 = new ArrayList();

    private ArrayList<InstitutionByPartner> dataInstitutionbypartner = new ArrayList();
    private HashMap<Integer, String> mapLocation = new HashMap();
    private HashMap<Integer, String> mapInstitution = new HashMap();

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

        presenter.getDataUser(Utils.getInstance().getObj());
        presenter.getDataInstitutionByPartner(Utils.getInstance().getObj());

        partner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                int key = getKey(selectedItemText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                int key = getKey(selectedItemText);
                if (key == locationEmpty) {
                    partner.setEnabled(false);
                } else {
                    partner.setEnabled(true);
                    setSpinnerInstitution(key);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void initViews() {

        cloveranim = AnimationUtils.loadAnimation(this, R.anim.cloveranim);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.fromdown);
        textSplash = findViewById(R.id.textsplash);
        splashtext = findViewById(R.id.splashUser);
        textHome = findViewById(R.id.textOptions);
        home = findViewById(R.id.menus);
        bgApp = findViewById(R.id.bgapp);
        bganim = AnimationUtils.loadAnimation(this, R.anim.bganim);
        menu = findViewById(R.id.menupointbutton);
        kitchen = findViewById(R.id.kitchenButton);
        school = findViewById(R.id.schoolButton);
        inkind = findViewById(R.id.inkindButton);
        walkers = findViewById(R.id.walkersButton);
        partner = findViewById(R.id.spinnerpartner);
        location = findViewById(R.id.spinnergeolocation);
        clover = findViewById(R.id.clover);
        settings = findViewById(R.id.settingsButton);
        partner = findViewById(R.id.spinnerpartner);
        location = findViewById(R.id.spinnergeolocation);
        userText = findViewById(R.id.userText);
        emailtext = findViewById(R.id.textemailhome);
        bar = findViewById(R.id.btn_recovery);
        partner.setEnabled(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menupointbutton:
                showPopup(view);
                break;
            case R.id.schoolButton:
                Intent intentSchool = new Intent(HomeActivity.this, SchoolActivity.class);
                startActivity(intentSchool);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.kitchenButton:
                Intent intent = new Intent(HomeActivity.this, NotSchoolActivity.class);
                intent.putExtra(NotSchoolActivity.SELECTED_ITEM, NotSchoolActivity.KITCHEN);
                startActivity(intent);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.walkersButton:
                Intent intentWalkers = new Intent(HomeActivity.this, NotSchoolActivity.class);
                intentWalkers.putExtra(NotSchoolActivity.SELECTED_ITEM, NotSchoolActivity.WALKERS);
                startActivity(intentWalkers);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.inkindButton:
                Intent intentInkind = new Intent(HomeActivity.this, NotSchoolActivity.class);
                intentInkind.putExtra(NotSchoolActivity.SELECTED_ITEM, NotSchoolActivity.INKIND);
                startActivity(intentInkind);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.settingsButton:
                Intent intentSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.btn_recovery:
                goHome();
        }
    }

    private int getKey(String selectedItemText) {
        int key = 0;
        for (Map.Entry<Integer, String> map : mapLocation.entrySet()) {
            if (map.getValue().equals(selectedItemText)) {
                key = map.getKey();
                break;
            }
        }
        return key;
    }

    @Override
    public void getInstitutionByPartnerSuccess(ArrayList<InstitutionByPartner> data) {
        dataInstitutionbypartner = data;
        setSpinnerLocation();
    }

    @Override
    public void getDataUserSuccess(DataUser dataUser) {
        Utils.getInstance().setDataUser(dataUser);
        splashtext.setText(Utils.getInstance().getDataUser().getUsername());
        userText.setText(Utils.getInstance().getDataUser().getUsername());
        emailtext.setText(Utils.getInstance().getDataUser().getEmail());
    }

    @Override
    public void getLogoutSuccess() {
        Toasty.success(HomeActivity.this, "Adios!", Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
        startActivity(intent);
        customType(HomeActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void responseError(String msg) {
        Toasty.warning(HomeActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }

    private void setSpinnerLocation() {
        mapLocation.put(0, "");
        for (int position = 0; position < dataInstitutionbypartner.size(); position++) {
            mapLocation.put(dataInstitutionbypartner.get(position).getGeolocation().getId(), dataInstitutionbypartner.get(position).getGeolocation().getName());
        }
        typesSpinner1 = new ArrayList(mapLocation.values());
        ArrayAdapter comboAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typesSpinner1);
        location.setAdapter(comboAdapterLocation);
    }


    private void setSpinnerInstitution(int keyLocation) {
        mapInstitution.clear();
        for (int position = 0; position < dataInstitutionbypartner.size(); position++) {
            if (keyLocation == dataInstitutionbypartner.get(position).getGeolocation().getId()) {
                mapInstitution.put(dataInstitutionbypartner.get(position).getId(), dataInstitutionbypartner.get(position).getName());
            }
        }
        typesSpinner2 = new ArrayList(mapInstitution.values());
        ArrayAdapter comboAdapterInstitution = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typesSpinner2);
        partner.setAdapter(comboAdapterInstitution);
    }

    public void goHome() {
        bgApp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textSplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        textHome.setVisibility(View.VISIBLE);
        home.setVisibility(View.VISIBLE);
        textHome.startAnimation(fromBottom);
        home.startAnimation(fromBottom);
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
}
