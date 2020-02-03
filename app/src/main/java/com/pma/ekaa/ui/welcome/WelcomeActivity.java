package com.pma.ekaa.ui.welcome;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.ui.forgot_password.PasswordActivity;
import com.pma.ekaa.ui.register.RegisterActivity;
import com.pma.ekaa.ui.welcome.presenter.WelcomePresenter;
import com.pma.ekaa.ui.welcome.presenter.WelcomePresenterImpl;
import com.pma.ekaa.utils.Location;
import com.pma.ekaa.utils.PreferencesHelper;
import java.util.ArrayList;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

import static maes.tech.intentanim.CustomIntent.customType;

public class WelcomeActivity extends BaseActivity implements WelcomeView, View.OnClickListener {

    private WelcomePresenter presenter;
    private ConstraintLayout loading;
    Button login;
    Button idioma;
    Button register;
    Button password;
    String animation = "fadein-to-fadeout";
    private Locale locale;
    Double latitude;
    Double longitude;

    private Configuration config = new Configuration();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        presenter = new WelcomePresenterImpl(this);
        loading = findViewById(R.id.progressBar);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        password = findViewById(R.id.optionsButton);
        idioma = findViewById(R.id.idiomaButton);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        password.setOnClickListener(this);
        idioma.setOnClickListener(this);

        getLocation();

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())  {
            case R.id.loginButton:
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                customType(WelcomeActivity.this, animation);
                break;
            case R.id.registerButton:
                showLoading();
                presenter.getPartnersData();
                break;
            case R.id.optionsButton:
                Intent intent2 = new Intent(WelcomeActivity.this, PasswordActivity.class);
                startActivity(intent2);
                customType(WelcomeActivity.this, animation);
                break;
            case R.id.idiomaButton:
                showDialog();
                break;
            default:

                break;
        }
    }


    private void showDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.language));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if(which == 0){
                    locale = new Locale("en");
                    config.locale = locale;

                }else if(which == 1){
                    locale = new Locale("es");
                    config.locale = locale;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(WelcomeActivity.this, WelcomeActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }



    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location local = new Location();
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) local);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
            }
        }
    }

    @Override
    public void getPartnersSuccess(ArrayList<Data> data) {
        hideLoading();
        if(!data.isEmpty()) {
            PreferencesHelper.setPreference(getApplication(), PreferencesHelper.KEY_PARTNER, new Gson().toJson(data));
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
            customType(WelcomeActivity.this, "fadein-to-fadeout");
        } else {
            responseError("Se ha producido un error");
        }
    }

    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.warning(WelcomeActivity.this, msg, Toast.LENGTH_SHORT, true).show();
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
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}


