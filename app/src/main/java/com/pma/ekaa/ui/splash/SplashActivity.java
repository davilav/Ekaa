package com.pma.ekaa.ui.splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.splash.presenter.SplashPresenter;
import com.pma.ekaa.ui.splash.presenter.SplashPresenterImpl;
import com.pma.ekaa.ui.welcome.WelcomeActivity;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends BaseActivity implements SplashView {

    ImageView logo;
    TextView webname;
    Animation fromtop,fromBottom;

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.pmaLogo);
        webname = findViewById(R.id.pmaText);

        presenter = new SplashPresenterImpl(this);

        setAnimation();

        requestPermission();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                customType(SplashActivity.this,"fadein-to-fadeout");
            }
        },2000);
    }

    private void setAnimation() {
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        logo.setAnimation(fromtop);
        webname.setAnimation(fromBottom);
    }

}
