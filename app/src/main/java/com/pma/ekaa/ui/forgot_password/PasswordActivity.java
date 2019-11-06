package com.pma.ekaa.ui.forgot_password;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenter;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenterImpl;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;

import static maes.tech.intentanim.CustomIntent.customType;

public class PasswordActivity extends BaseActivity implements PasswordView, View.OnClickListener {

    private Button memberButton;
    private ButtonProgressBar bar;

    private EditText email, confirmemail;
    private CheckBox termsAndConditions;
    private PasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        presenter = new PasswordPresenterImpl(this);

        memberButton = findViewById(R.id.memberButton);
        email = findViewById(R.id.email);
        confirmemail = findViewById(R.id.confirmemail);
        termsAndConditions = findViewById(R.id.chkBox1);
        bar = findViewById(R.id.btn_recovery);

        bar.setOnClickListener(this);
        memberButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_recovery:
                bar.startLoader();

                String email1 = email.getText().toString();
                String emailConfirm = confirmemail.getText().toString();

                if(TextUtils.isEmpty(email1)){
                    Toasty.warning(this, "Debes ingresar tu Email", Toast.LENGTH_SHORT).show();
                    bar.stopLoader();
                } else if (TextUtils.isEmpty(emailConfirm)){
                    Toasty.warning(this, "Debes confirmar tu Email", Toast.LENGTH_SHORT).show();
                    bar.stopLoader();
                } else if(!email1.equals(emailConfirm)){
                    Toasty.warning(this, "Los correos no coinciden", Toast.LENGTH_SHORT).show();
                    bar.stopLoader();
                } else if(!termsAndConditions.isChecked()){
                    Toasty.warning(this, "No se han aceptado los terminos y condiciones", Toast.LENGTH_SHORT).show();
                    bar.stopLoader();
                } else {
                    presenter.recoveryPassword(email1);
                }
                break;
            case R.id.memberButton:
                Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                customType(PasswordActivity.this,"fadein-to-fadeout");
                break;
        }
    }

    @Override
    public void passwordSuccess(String msg) {
        bar.stopLoader();
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PasswordActivity.this, WelcomeActivity.class);
        startActivity(intent);
        customType(PasswordActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void passwordError(String msg) {
        bar.stopLoader();
        Toasty.warning(this, msg, Toast.LENGTH_SHORT).show();
    }
}








