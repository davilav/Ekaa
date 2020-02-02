package com.pma.ekaa.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.splash.presenter.SplashPresenter;
import com.pma.ekaa.ui.splash.presenter.SplashPresenterImpl;
import com.pma.ekaa.ui.welcome.WelcomeActivity;
import com.pma.ekaa.utils.ProgressBarAnim;

import es.dmoral.toasty.Toasty;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends BaseActivity implements SplashView {

    ImageView logo;
    TextView webname;
    TextView loading;
    Animation fromtop;
    Animation fromBottom;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logo = findViewById(R.id.pmaLogo);
        webname = findViewById(R.id.pmaText);
        progressBar = findViewById(R.id.progressLoading);
        loading = findViewById(R.id.textLoading);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        SplashPresenter presenter = new SplashPresenterImpl(this, getApplication());

        setAnimation();

        presenter.getData();

    }

    private void setAnimation() {
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        logo.setAnimation(fromtop);
        webname.setAnimation(fromBottom);
        progressBar.setAnimation(fromBottom);
        loading.setAnimation(fromBottom);
        progressAnimation();
    }

    public void progressAnimation(){

        ProgressBarAnim anim = new ProgressBarAnim(this,progressBar,loading,0f,100f);
        anim.setDuration(14500);
        progressBar.setAnimation(anim);

    }

    @Override
    public void getDataSuccess() {

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
    public void loginError(String msg) {
        Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show();
    }
}
