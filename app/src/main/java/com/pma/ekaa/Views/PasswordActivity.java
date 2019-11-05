package com.pma.ekaa.Views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Login;
import com.pma.ekaa.models.Password;
import com.pma.ekaa.models.User;
import com.pma.ekaa.models.UserLog;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class PasswordActivity extends AppCompatActivity {

    Button memberButton;
    CardView passwordlayout;
    Animation bganim,cloveranim;
    Animation fromtop,fromBottom;
    EditText txtemail,txtconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

    }
    public void initViews(){
        memberButton = findViewById(R.id.memberButton);
        cloveranim = AnimationUtils.loadAnimation(this,R.anim.cloveranim);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.fromdown);
        passwordlayout = findViewById(R.id.passwordlayout);
        txtemail = findViewById(R.id.email);
        txtconfirm = findViewById(R.id.confirmemail);

        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goPassword();
                        bar.stopLoader();

                    }
                }, 4000);
            }
        });

        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                customType(PasswordActivity.this,"fadein-to-fadeout");
            }
        });
    }

    public void goPassword(){

        final String email = txtemail.getText().toString();
        final String emailconfirm = txtconfirm.getText().toString();

        Password password = new Password(email);

        if (TextUtils.isEmpty(email)) {
            Toasty.warning(PasswordActivity.this, "Debes ingresar tu email!", Toast.LENGTH_SHORT, true).show();
            return;
        }


        if (TextUtils.isEmpty(emailconfirm)) {
            Toasty.warning(PasswordActivity.this, "Debes confirmar tu email!", Toast.LENGTH_SHORT, true).show();
            return;
        }

        if(email.equals(emailconfirm)){
            Call<User> call = ApiClient.getInstance().getApi().password(password);
            call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toasty.success(PasswordActivity.this, "Se ha enviado a tu correo el enlace para reestablecer la contraseña!", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(PasswordActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    customType(PasswordActivity.this, "fadein-to-fadeout");
                    //token =  response.body().getToken();

                }
                else {
                    Toasty.error(PasswordActivity.this, "Falló la conexión con el servidor.", Toast.LENGTH_SHORT, true).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        }else{
            Toasty.warning(PasswordActivity.this, "Los correos electronicos ingresados no coinciden.", Toast.LENGTH_SHORT, true).show();

        }
    }
}








