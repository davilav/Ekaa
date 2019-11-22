package com.pma.ekaa.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.UserLog;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.forgot_password.PasswordActivity;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.login.presenter.LoginPresenter;
import com.pma.ekaa.ui.login.presenter.LoginPresenterImpl;
import com.pma.ekaa.utils.Utils;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    Button passButton;
    ImageView eyeButton;
    EditText txtEmail, txtPassword;
    ButtonProgressBar bar;

    private LoginPresenter presenter;

    private static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenterImpl(this);

        passButton = findViewById(R.id.passwordButton);
        txtEmail = findViewById(R.id.emailText);
        txtPassword = findViewById(R.id.passwordText);
        eyeButton = findViewById(R.id.eyeButton);
        bar = findViewById(R.id.btn_recovery);

        eyeButton.setOnClickListener(this);
        passButton.setOnClickListener(this);
        bar.setOnClickListener(this);

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
                    Toasty.warning(LoginActivity.this, "Debes ingresar tu usuario!", Toast.LENGTH_SHORT, true).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toasty.warning(LoginActivity.this, "Debes ingresar tu contraseña!", Toast.LENGTH_SHORT, true).show();
                } else {
                    bar.startLoader();
                    login();
                }
                break;
        }
    }

    private void login() {
        presenter.loginUser(txtEmail.getText().toString(), txtPassword.getText().toString());
    }


    @Override
    public void loginSuccess(UserLog response) {
        bar.stopLoader();
        Utils.getInstance().setDataUser(response);
        Toasty.success(LoginActivity.this, "Bienvenido!", Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        customType(LoginActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void loginError(String msg) {
        bar.stopLoader();
        Toasty.warning(LoginActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }
}



