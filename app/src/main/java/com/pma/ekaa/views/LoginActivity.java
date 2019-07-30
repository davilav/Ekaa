package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.User;

import java.util.ArrayList;

import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppCompatActivity implements Callback<ArrayList<User>> {

    Button passButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passButton = findViewById(R.id.passwordButton);

        Call<ArrayList<User>> call = ApiClient.getApiService().getUsers();
        call.enqueue(this);

        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bar.stopLoader();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        customType(LoginActivity.this,"fadein-to-fadeout");
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

    @Override
    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
        if(response.isSuccessful()) {
           ArrayList<User> users =  response.body();
            Log.d("onResponse user","Size of user => "+users.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<User>> call, Throwable t) {

    }
}


