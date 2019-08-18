package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.pma.ekaa.models.User;
import com.pma.ekaa.models.UserLog;

import java.io.IOException;
import java.util.ArrayList;

import github.ishaan.buttonprogressbar.ButtonProgressBar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppCompatActivity {

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
                login();

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
           Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_SHORT).show();
           return;
       }


       if (TextUtils.isEmpty(pass)) {
           Toast.makeText(this, "Se debe ingresar una constraseña", Toast.LENGTH_SHORT).show();
           return;
       }


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               bar.stopLoader();

           }
       }, 4000);

       Call<UserLog> call = ApiClient.getInstance().getApi().login(login);
       call.enqueue(new Callback<UserLog>() {
           @Override
           public void onResponse(Call<UserLog> call, Response<UserLog> response) {

               if (response.isSuccessful()) {
                   Toast.makeText(LoginActivity.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                   startActivity(intent);
                   customType(LoginActivity.this, "fadein-to-fadeout");
                   token =  response.body().getToken();

               }
               else {
                   Toast.makeText(LoginActivity.this, "inicio de sesion erroneo", Toast.LENGTH_SHORT).show();

               }
           }

           @Override
           public void onFailure(Call<UserLog> call, Throwable t) {
               Toast.makeText(LoginActivity.this, "inicio de sesion fallido", Toast.LENGTH_SHORT).show();

           }
       });
   }
   private void getToken(){
       Call<ResponseBody> call = ApiClient.getInstance().getApi().getToken(token);
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

               if (response.isSuccessful()) {
                   try {
                       Toast.makeText(LoginActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                   }catch (IOException e) {
                       e.printStackTrace();
                   }

               } else {
                   Toast.makeText(LoginActivity.this,"NOT TOKEN", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Toast.makeText(LoginActivity.this, "NOT TOKEN", Toast.LENGTH_SHORT).show();
           }
       });


   }
}


