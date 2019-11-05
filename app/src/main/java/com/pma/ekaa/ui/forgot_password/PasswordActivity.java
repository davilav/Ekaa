package com.pma.ekaa.ui.forgot_password;

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
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenter;
import com.pma.ekaa.ui.forgot_password.presenter.PasswordPresenterImpl;
import com.pma.ekaa.ui.login.LoginActivity;

import github.ishaan.buttonprogressbar.ButtonProgressBar;

import static maes.tech.intentanim.CustomIntent.customType;

public class PasswordActivity extends BaseActivity implements PasswordView, View.OnClickListener {

    private Button memberButton;
    private CardView passwordlayout;
    private LinearLayout passwordview;
    private Animation bganim,cloveranim;
    private Animation fromtop,fromBottom;
    private ImageView bgapp;
    private ButtonProgressBar bar;

    private PasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        presenter = new PasswordPresenterImpl(this);

        memberButton = findViewById(R.id.memberButton);
        passwordlayout = findViewById(R.id.passwordlayout);
        passwordview  = findViewById(R.id.passwordview);
        bar = findViewById(R.id.btn_recovery);

        loadAnimation();

        bar.setOnClickListener(this);
        memberButton.setOnClickListener(this);

    }

    private void loadAnimation() {
        cloveranim = AnimationUtils.loadAnimation(this,R.anim.cloveranim);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.fromdown);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_recovery:
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goPassword();
                        bar.stopLoader();

                    }
                }, 4000);
                break;
            case R.id.memberButton:
                Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                customType(PasswordActivity.this,"fadein-to-fadeout");
                break;
        }
    }

    public void goPassword(){
        passwordlayout.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        passwordview.setVisibility(View.VISIBLE);

        passwordview.startAnimation(fromBottom);


    }


}








