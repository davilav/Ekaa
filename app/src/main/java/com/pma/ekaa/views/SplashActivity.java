package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends AppCompatActivity implements Callback<ArrayList<User>> {

    ImageView logo;
    TextView webname;
    Animation fromtop,fromBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Call<ArrayList<User>> call = ApiClient.getApiService().getUsers();
        call.enqueue(this);

        logo = findViewById(R.id.pmaLogo);
        webname = findViewById(R.id.pmaText);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        logo.setAnimation(fromtop);
        webname.setAnimation(fromBottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                customType(SplashActivity.this,"fadein-to-fadeout");
            }
        },2000);
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
