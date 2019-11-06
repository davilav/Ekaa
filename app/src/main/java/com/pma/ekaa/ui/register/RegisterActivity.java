package com.pma.ekaa.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.models.User;
import com.pma.ekaa.ui.register.presenter.RegisterPresenter;
import com.pma.ekaa.ui.register.presenter.RegisterPresenterImpl;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {

    private Button memberButton;
    private EditText emailtxt, passtxt, pass2txt, usernametxt;
    private ButtonProgressBar bar;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        memberButton = findViewById(R.id.memberButton);
        emailtxt = findViewById(R.id.emailtext);
        passtxt = findViewById(R.id.passtext);
        pass2txt = findViewById(R.id.confirmtext);
        usernametxt = findViewById(R.id.roltext);
        bar = findViewById(R.id.btn_recovery);
        presenter = new RegisterPresenterImpl(this);

        bar.setOnClickListener(this);
        memberButton.setOnClickListener(this);

    }

    private void register() {

        final String email = emailtxt.getText().toString();
        final String pass = passtxt.getText().toString();
        final String pass2 = pass2txt.getText().toString();
        final String user = usernametxt.getText().toString();
        final Integer partner = 1;
        final Integer rol = 1;

        //Register register = new Register(user, email, pass, pass2, partner, rol);

        if (TextUtils.isEmpty(email)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar tu Email", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailtxt.setError("Enter a valid Email");
            emailtxt.requestFocus();
            bar.stopLoader();
        } else if (TextUtils.isEmpty(pass)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else if (TextUtils.isEmpty(user)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar un usuario", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else if (TextUtils.isEmpty(pass2)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else {
            presenter.setRegister(user, email, pass, pass2, partner, rol);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recovery: {
                bar.startLoader();
                register();
                break;
            }
            case R.id.memberButton: {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                customType(RegisterActivity.this, "fadein-to-fadeout");
                break;
            }
        }
    }

    @Override
    public void registerSuccess() {
        bar.stopLoader();
        Toasty.success(RegisterActivity.this, "Por favor confime la direccion de correo", Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
        startActivity(intent);
        customType(RegisterActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void registerError(String msg) {
        bar.stopLoader();
        Toasty.success(RegisterActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }
}
