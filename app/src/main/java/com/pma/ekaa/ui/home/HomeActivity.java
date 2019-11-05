package com.pma.ekaa.ui.home;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
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

import androidx.appcompat.app.AppCompatActivity;

import com.pma.ekaa.R;
import com.pma.ekaa.Views.InkindActivity;
import com.pma.ekaa.Views.KitchenActivity;
import com.pma.ekaa.Views.SchoolActivity;
import com.pma.ekaa.Views.SettingsActivity;
import com.pma.ekaa.Views.WalkersActivity;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.models.UserLog;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.home.presenter.HomePresenter;
import com.pma.ekaa.ui.home.presenter.HomePresenterImpl;
import com.pma.ekaa.utils.Utils;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        presenter = new HomePresenterImpl(this);

        initViews();

        menu.setOnClickListener(this);
        kitchen.setOnClickListener(this);
        school.setOnClickListener(this);
        inkind.setOnClickListener(this);
        walkers.setOnClickListener(this);
        settings.setOnClickListener(this);

        presenter.getDataInstitutionByPartner(Utils.getInstance().getObj());
        presenter.getDataUser(Utils.getInstance().getObj());

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
            case R.id.kitchenButton:
                RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.schoolButton:
                /*RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);*/
                Intent intentSchool = new Intent(HomeActivity.this, SchoolActivity.class);
                startActivity(intentSchool);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.inkindButton:
                /*RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);*/
                Intent intentInkind = new Intent(HomeActivity.this, InkindActivity.class);
                startActivity(intentInkind);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.walkersButton:
                /*RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);*/
                Intent intentWalkers = new Intent(HomeActivity.this, WalkersActivity.class);
                startActivity(intentWalkers);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.settingsButton:
                Intent intentSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                customType(HomeActivity.this, "fadein-to-fadeout");
                break;
            case R.id.btn_recovery:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goHome();
                        bar.stopLoader();

                    }
                }, 4000);
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
    public void responseError(String msg) {
        //MOstrar un mensaje de error
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

    public void capturateUserData() {
        retrofit2.Call<DataUser> call = ApiClient.getInstance().getApi().getDataUser("Token " + token);
        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {

                splashtext.setText(response.body().getUsername());
                userText.setText(response.body().getUsername());
                emailtext.setText(response.body().getEmail());

            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                int a = 0;
            }
        });
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
                logout();
                return true;
            default:
                return false;
        }
    }

    private void logout() {
        Call<UserLog> call = ApiClient.getInstance().getApi().login();
        call.enqueue(new Callback<UserLog>() {
            @Override
            public void onResponse(Call<UserLog> call, Response<UserLog> response) {
                if (response.isSuccessful()) {
                    Toasty.success(HomeActivity.this, "Adios!", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    customType(HomeActivity.this, "fadein-to-fadeout");
                } else {
                    Toast.makeText(HomeActivity.this, "Cierre de sesion fallida", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLog> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Fallo al cerrar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
