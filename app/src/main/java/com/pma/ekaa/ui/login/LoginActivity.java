package com.pma.ekaa.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.UserLog;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.forgot_password.PasswordActivity;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.login.presenter.LoginPresenter;
import com.pma.ekaa.ui.login.presenter.LoginPresenterImpl;
import com.pma.ekaa.utils.Utils;

import es.dmoral.toasty.Toasty;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener{

    private ConstraintLayout loading;
    Button passButton;
    ImageView eyeButton;
    EditText txtEmail;
    EditText txtPassword;
    Button bar;

    private LoginPresenter presenter;

    private boolean isEmailEmpty = false;
    private boolean isPasswordEmpty = false;
    private boolean isTermsEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenterImpl(this);

        loading = findViewById(R.id.progressBar);
        passButton = findViewById(R.id.passwordButton);
        txtEmail = findViewById(R.id.emailText);
        txtPassword = findViewById(R.id.passwordText);
        eyeButton = findViewById(R.id.eyeButton);
        CheckBox checkTerms = findViewById(R.id.chk_terms);
        bar = findViewById(R.id.btn_recovery);

        eyeButton.setOnClickListener(this);
        passButton.setOnClickListener(this);
        bar.setOnClickListener(this);

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Antes de cambiar el texto

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Durante el cambio

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEmailEmpty = editable.length() > 0;
                validateFields();
            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Antes de cambiar el texto
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Durante el cambio

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isPasswordEmpty = editable.length() > 0;
                validateFields();
            }
        });

        checkTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                isTermsEmpty = state;
                validateFields();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eyeButton:
                if (txtPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeButton.setBackgroundResource(R.drawable.eyehide);
                } else {
                    txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeButton.setBackgroundResource(R.drawable.eyeshow);
                }
                txtPassword.setSelection(txtPassword.getText().length());
                break;
            case R.id.passwordButton:
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(intent);
                customType(LoginActivity.this, "fadein-to-fadeout");
                break;
            case R.id.btn_recovery:
                String email = txtEmail.getText().toString();
                String pass = txtPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toasty.warning(LoginActivity.this, getResources().getString(R.string.ingresaruser), Toast.LENGTH_SHORT, true).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toasty.warning(LoginActivity.this, getResources().getString(R.string.ingresarpass), Toast.LENGTH_SHORT, true).show();
                } else {
                    showLoading();
                    login();
                }
                break;
            default:

                break;
        }
    }

    private void login() {
        presenter.loginUser(txtEmail.getText().toString(), txtPassword.getText().toString());
    }

    private void validateFields() {

        bar.setEnabled(isEmailEmpty && isPasswordEmpty && isTermsEmpty);
    }

    @Override
    public void loginSuccess(UserLog response) {
        hideLoading();
        Utils.getInstance().setDataUser(response);
        Toasty.success(LoginActivity.this, getResources().getString(R.string.welcomeuser), Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        customType(LoginActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void loginError(String msg) {
        hideLoading();
        Toasty.warning(LoginActivity.this, msg, Toast.LENGTH_SHORT, true).show();
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



