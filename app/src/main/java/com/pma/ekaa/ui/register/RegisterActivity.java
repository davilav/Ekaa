package com.pma.ekaa.ui.register;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.login.LoginActivity;
import com.pma.ekaa.ui.register.presenter.RegisterPresenter;
import com.pma.ekaa.ui.register.presenter.RegisterPresenterImpl;
import com.pma.ekaa.utils.PreferencesHelper;

import es.dmoral.toasty.Toasty;

import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {

    private ConstraintLayout loading;
    private EditText nameUser;
    private EditText lastNameUser;
    private EditText emailUser;
    private EditText user;
    private EditText passUser;
    private EditText confirmPassUser;
    private EditText userPartner;
    private EditText userRol;
    private ImageView eyePass;
    private ImageView eyeConfirm;
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
        Button memberButton = findViewById(R.id.memberButton);
        eyePass = findViewById(R.id.hidePassword);
        eyeConfirm = findViewById(R.id.hideConfirm);
        Button bar = findViewById(R.id.btn_recovery);

        presenter = new RegisterPresenterImpl(this);

        userRol.setText("1");
        userRol.setEnabled(false);

        bar.setOnClickListener(this);
        memberButton.setOnClickListener(this);
        userPartner.setOnClickListener(this);
        eyeConfirm.setOnClickListener(this);
        eyePass.setOnClickListener(this);

    }

    private void register() {

        final String firstName = nameUser.getText().toString();
        final String lastName = lastNameUser.getText().toString();
        final String email = emailUser.getText().toString();
        final String userName = user.getText().toString();
        final String pass = passUser.getText().toString();
        final String pass2 = confirmPassUser.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toasty.warning(RegisterActivity.this, getResources().getString(R.string.ingresaremail), Toast.LENGTH_SHORT, true).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailUser.setError(getResources().getString(R.string.validemail));
            emailUser.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            Toasty.warning(RegisterActivity.this, getResources().getString(R.string.ingresarpass), Toast.LENGTH_SHORT, true).show();
        } else if (TextUtils.isEmpty(userName)) {
            Toasty.warning(RegisterActivity.this, getResources().getString(R.string.ingresaruser), Toast.LENGTH_SHORT, true).show();
        } else if (TextUtils.isEmpty(pass2)) {
            Toasty.warning(RegisterActivity.this, getResources().getString(R.string.confirmarspass), Toast.LENGTH_SHORT, true).show();
        } else {
            presenter.setRegister(userName, email, pass, pass2, firstName, lastName, partnerID, Integer.parseInt(userRol.getText().toString()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recovery: {
                showLoading();
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
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                partnerID = data.getId();
                                userPartner.setText(data.getName());
                            }
                        }).show(getSupportFragmentManager(), "");
                break;
            case R.id.hidePassword:
                if (passUser.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    passUser.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyePass.setBackgroundResource(R.drawable.eyehide);
                } else {
                    passUser.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyePass.setBackgroundResource(R.drawable.eyeshow);
                }
                passUser.setSelection(passUser.getText().length());
                break;

            case R.id.hideConfirm:
                if (confirmPassUser.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    confirmPassUser.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeConfirm.setBackgroundResource(R.drawable.eyehide);
                } else {
                    confirmPassUser.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeConfirm.setBackgroundResource(R.drawable.eyeshow);
                }
                confirmPassUser.setSelection(confirmPassUser.getText().length());
                break;
            default:
                break;
        }
    }

    @Override
    public void registerSuccess() {
        hideLoading();
        Toasty.success(RegisterActivity.this, getResources().getString(R.string.confirmaremail), Toast.LENGTH_SHORT, true).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        customType(RegisterActivity.this, "fadein-to-fadeout");
    }

    @Override
    public void registerError(String msg) {
        hideLoading();
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
