package com.pma.ekaa.Views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Login;
import com.pma.ekaa.models.RequestUser;
import com.pma.ekaa.models.UserLog;
import com.pma.ekaa.models.Utils;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppCompatActivity{

    Button passButton;
    ImageView eyeButton;
    EditText txtEmail,txtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        passButton = findViewById(R.id.passwordButton);
        txtEmail = findViewById(R.id.emailText);
        txtPassword = findViewById(R.id.passwordText);
        eyeButton = findViewById(R.id.eyeButton);


        eyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeButton.setBackgroundResource(R.drawable.eyehide);
                } else {
                    txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeButton.setBackgroundResource(R.drawable.eyeshow);
                }
                txtPassword.setSelection(txtPassword.getText().length());

            }
        });



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
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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
               Toasty.warning(LoginActivity.this, "Fallo la conexion con el servidor", Toast.LENGTH_SHORT, true).show();

           }
       });
   }

   }



