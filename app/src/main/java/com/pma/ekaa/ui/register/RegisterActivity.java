package com.pma.ekaa.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.home.HomeActivity;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.models.User;
import com.pma.ekaa.ui.register.presenter.RegisterPresenter;
import com.pma.ekaa.ui.register.presenter.RegisterPresenterImpl;
import com.pma.ekaa.ui.welcome.WelcomeActivity;
import com.pma.ekaa.utils.PreferencesHelper;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {

    private ConstraintLayout loading;
    private Button memberButton;
    private EditText nameUser, lastNameUser, emailUser, user, passUser, confirmPassUser, userPartner, userRol;
    private ButtonProgressBar bar;
    private RegisterPresenter presenter;
    private int partnerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loading = findViewById(R.id.progressBar);
        nameUser = findViewById(R.id.nameUser);
        lastNameUser = findViewById(R.id.lastnameUser);
        emailUser = findViewById(R.id.emailUser);
        user = findViewById(R.id.user);
        passUser = findViewById(R.id.passUser);
        confirmPassUser = findViewById(R.id.confirmPassUser);
        userPartner = findViewById(R.id.userPartner);
        userRol = findViewById(R.id.userRol);
        memberButton = findViewById(R.id.memberButton);
        bar = findViewById(R.id.btn_recovery);

        presenter = new RegisterPresenterImpl(this);

        userRol.setText("1");
        userRol.setEnabled(false);

        bar.setOnClickListener(this);
        memberButton.setOnClickListener(this);
        userPartner.setOnClickListener(this);

    }

    private void register() {

        final String firstName = nameUser.getText().toString();
        final String lastName = lastNameUser.getText().toString();
        final String email = emailUser.getText().toString();
        final String userName = user.getText().toString();
        final String pass = passUser.getText().toString();
        final String pass2 = confirmPassUser.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar tu Email", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailUser.setError("Enter a valid Email");
            emailUser.requestFocus();
            bar.stopLoader();
        } else if (TextUtils.isEmpty(pass)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else if (TextUtils.isEmpty(userName)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar un usuario", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else if (TextUtils.isEmpty(pass2)) {
            Toasty.warning(RegisterActivity.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT, true).show();
            bar.stopLoader();
        } else {
            presenter.setRegister(userName, email, pass, pass2, firstName, lastName, partnerID, Integer.parseInt(userRol.getText().toString()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recovery: {
                showLoading();
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
            case R.id.userPartner:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getApplicationContext(), PreferencesHelper.KEY_PARTNER, ""),
                        false,
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                partnerID = data.getId();
                                userPartner.setText(data.getName());
                            }
                        }).show(getSupportFragmentManager(), "");
                break;
        }
    }

    @Override
    public void registerSuccess() {
        hideLoading();
        bar.stopLoader();
        Toasty.success(RegisterActivity.this, "Se ha enviando un mensaje de texto", Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        customType(RegisterActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void registerError(String msg) {
        hideLoading();
        bar.stopLoader();
        Toasty.success(RegisterActivity.this, msg, Toast.LENGTH_SHORT, true).show();
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
