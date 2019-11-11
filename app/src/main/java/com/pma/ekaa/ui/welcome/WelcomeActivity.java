package com.pma.ekaa.ui.welcome;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.ui.forgot_password.PasswordActivity;
import com.pma.ekaa.ui.register.RegisterActivity;
import com.pma.ekaa.utils.PreferencesHelper;

import java.util.Locale;

import static maes.tech.intentanim.CustomIntent.customType;

public class WelcomeActivity extends BaseActivity implements WelcomeView {

    Button login, register, password, idioma;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        password = findViewById(R.id.optionsButton);
        idioma = findViewById(R.id.idiomaButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                customType(WelcomeActivity.this, "fadein-to-fadeout");
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
                customType(WelcomeActivity.this, "fadein-to-fadeout");

            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, PasswordActivity.class);
                startActivity(intent);
                customType(WelcomeActivity.this, "fadein-to-fadeout");
            }
        });

        idioma.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }
                });
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
                switch (which) {
                    case 0:
                        locale = new Locale("en");
                        config.locale = locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale = locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(WelcomeActivity.this, WelcomeActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }

}


