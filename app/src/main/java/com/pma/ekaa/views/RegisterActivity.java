package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Register;
import com.pma.ekaa.models.User;
import com.pma.ekaa.models.UserLog;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterActivity extends AppCompatActivity {

    Button memberButton;
    EditText emailtxt,passtxt,pass2txt,usernametxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        memberButton = findViewById(R.id.memberButton);
        emailtxt = findViewById(R.id.emailtext);
        passtxt = findViewById(R.id.passtext);
        pass2txt = findViewById(R.id.confirmtext);
        usernametxt = findViewById(R.id.roltext);


        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        register();
                        bar.stopLoader();

                    }
                }, 4000);
            }
        });

        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                customType(RegisterActivity.this,"fadein-to-fadeout");
            }
        });
    }

 void register(){

        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        final String email = emailtxt.getText().toString();
        final String pass = passtxt.getText().toString();
        final String pass2 = pass2txt.getText().toString();
        final String user = usernametxt.getText().toString();

        Register register = new Register(user,email,pass,pass2);

     if (TextUtils.isEmpty(email)) {
         Toasty.warning(RegisterActivity.this, "Debes ingresar tu email", Toast.LENGTH_SHORT, true).show();
         return;
     }

     if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
         emailtxt.setError("Enter a valid email");
         emailtxt.requestFocus();
         return;
     }


     if (TextUtils.isEmpty(pass)) {
         Toasty.warning(RegisterActivity.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT, true).show();
         return;
     }

     if (TextUtils.isEmpty(user)) {
         Toasty.warning(RegisterActivity.this, "Debes ingresar un usuario", Toast.LENGTH_SHORT, true).show();
         return;
     }


     if (TextUtils.isEmpty(pass2)) {
         Toasty.warning(RegisterActivity.this, "Debes ingresar una contraseña", Toast.LENGTH_SHORT, true).show();
         return;
     }



     Call<User> call = ApiClient.getInstance().getApi().register(register);
     call.enqueue(new Callback<User>() {
         @Override
         public void onResponse(Call<User> call, Response<User> response) {
             if (response.isSuccessful()) {
                 Toasty.success(RegisterActivity.this, "Se ha registrado exitosamente!", Toast.LENGTH_SHORT, true).show();
                 Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                 startActivity(intent);
                 customType(RegisterActivity.this, "fadein-to-fadeout");
                 //token =  response.body().getToken();

             }
             else {
                 Toasty.error(RegisterActivity.this, "Error al crear usuario.", Toast.LENGTH_SHORT, true).show();

             }
         }

         @Override
         public void onFailure(Call<User> call, Throwable t) {
             Toasty.warning(RegisterActivity.this, "Fallo al iniciar sesion", Toast.LENGTH_SHORT, true).show();
         }
         
     });
    }
}
