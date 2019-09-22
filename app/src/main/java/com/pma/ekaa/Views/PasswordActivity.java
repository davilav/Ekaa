package com.pma.ekaa.Views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.pma.ekaa.R;

import github.ishaan.buttonprogressbar.ButtonProgressBar;

import static maes.tech.intentanim.CustomIntent.customType;

public class PasswordActivity extends AppCompatActivity {

    Button memberButton;
    CardView passwordlayout;
    LinearLayout passwordview;
    Animation bganim,cloveranim;
    Animation fromtop,fromBottom;
    ImageView bgapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        memberButton = findViewById(R.id.memberButton);
        cloveranim = AnimationUtils.loadAnimation(this,R.anim.cloveranim);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.fromdown);
        passwordlayout = findViewById(R.id.passwordlayout);
        passwordview  = findViewById(R.id.passwordview);

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
        passwordlayout.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        passwordview.setVisibility(View.VISIBLE);

        passwordview.startAnimation(fromBottom);


    }
}








