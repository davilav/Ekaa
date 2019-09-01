package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.apis.ApiInterface;
import com.pma.ekaa.models.Login;
import com.pma.ekaa.models.RequestUser;
import com.pma.ekaa.models.User;
import com.pma.ekaa.models.UserLog;
import com.pma.ekaa.models.Utils;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppCompatActivity{

    Button passButton;
    EditText txtEmail,txtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passButton = findViewById(R.id.passwordButton);
        txtEmail = findViewById(R.id.emailText);
        txtPassword = findViewById(R.id.passwordText);


        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        login();
                        bar.stopLoader();

                    }
                }, 4000);

            }
        });

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(intent);
                customType(LoginActivity.this,"fadein-to-fadeout");
            }
        });

    }

    private static String token;

   private void login(){

       final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
       final String email = txtEmail.getText().toString();
       final String pass = txtPassword.getText().toString();

       Login login = new Login(txtEmail.getText().toString(),txtPassword.getText().toString());

       if (TextUtils.isEmpty(email)) {
           Toasty.warning(LoginActivity.this, "Debes ingresar tu usuario!", Toast.LENGTH_SHORT, true).show();
           return;
       }


       if (TextUtils.isEmpty(pass)) {
           Toasty.warning(LoginActivity.this, "Debes ingresar tu contraseña!", Toast.LENGTH_SHORT, true).show();
           return;
       }


       Call<UserLog> call = ApiClient.getInstance().getApi().login(login);
       call.enqueue(new Callback<UserLog>() {
           @Override
           public void onResponse(Call<UserLog> call, Response<UserLog> response) {

               if (response.isSuccessful()) {
                   token =  response.body().getKey();
                   Toasty.success(LoginActivity.this, "Bienvenido!", Toast.LENGTH_SHORT, true).show();
                   RequestUser obj = new RequestUser();
                   obj.setToken(token);
                   Utils.getInstance().setObj(obj);
                   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                   startActivity(intent);
                   customType(LoginActivity.this, "fadein-to-fadeout");
               }
               else {
                   Toasty.error(LoginActivity.this, "Error al iniciar sesion.", Toast.LENGTH_SHORT, true).show();

               }
           }

           @Override
           public void onFailure(Call<UserLog> call, Throwable t) {
               Toasty.warning(LoginActivity.this, "Fallo al iniciar sesion", Toast.LENGTH_SHORT, true).show();

           }
       });
   }

   }



