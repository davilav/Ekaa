package com.pma.ekaa.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pma.ekaa.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView webname;
    Animation fromtop,fromBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



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

}
