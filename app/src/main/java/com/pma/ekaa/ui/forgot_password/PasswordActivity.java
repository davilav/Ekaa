package com.pma.ekaa.ui.forgot_password;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenter;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenterImpl;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import es.dmoral.toasty.Toasty;

import static maes.tech.intentanim.CustomIntent.customType;

public class PasswordActivity extends BaseActivity implements PasswordView, View.OnClickListener {

    private ConstraintLayout loading;
    private Button memberButton;
    private Button bar;

    private EditText email, confirmemail;
    private CheckBox termsAndConditions;
    private PasswordPresenter presenter;

    private boolean isEmailEmpty = false;
    private boolean isConfirmEmailEmpty = false;
    private boolean isTermsEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        presenter = new PasswordPresenterImpl(this);

        loading = findViewById(R.id.progressBar);
        memberButton = findViewById(R.id.memberButton);
        email = findViewById(R.id.email);
        confirmemail = findViewById(R.id.confirmemail);
        termsAndConditions = findViewById(R.id.chkBox1);
        bar = findViewById(R.id.btn_recovery);

        bar.setOnClickListener(this);
        memberButton.setOnClickListener(this);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEmailEmpty = editable.length() > 0;
                validateFields();
            }
        });

        confirmemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isConfirmEmailEmpty = editable.length() > 0;
                validateFields();
            }
        });

        termsAndConditions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                isTermsEmpty = state;
                validateFields();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_recovery:

                String email1 = email.getText().toString();
                String emailConfirm = confirmemail.getText().toString();

                if(TextUtils.isEmpty(email1)){
                    Toasty.warning(this,getResources().getString(R.string.ingresaremail), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(emailConfirm)){
                    Toasty.warning(this, getResources().getString(R.string.confirmemail), Toast.LENGTH_SHORT).show();
                } else if(!email1.equals(emailConfirm)){
                    Toasty.warning(this, getResources().getString(R.string.coincidircorreos), Toast.LENGTH_SHORT).show();
                } else if(!termsAndConditions.isChecked()){
                    Toasty.warning(this, getResources().getString(R.string.checkboxterminos), Toast.LENGTH_SHORT).show();
                } else {
                    showLoading();
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
        hideLoading();
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PasswordActivity.this, WelcomeActivity.class);
        startActivity(intent);
        customType(PasswordActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void passwordError(String msg) {
        hideLoading();
        Toasty.warning(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void validateFields() {
        bar.setEnabled(isEmailEmpty && isConfirmEmailEmpty && isTermsEmpty);
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








