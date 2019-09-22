package com.pma.ekaa.Views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.RequestUser;
import com.pma.ekaa.models.UserLog;
import com.pma.ekaa.models.Utils;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView kitchen,school,inkind,walkers,cloud,url,settings,info,menu,bgApp;
    ImageView clover;
    String token = Utils.getInstance().getObj().getToken();

    Animation bganim,cloveranim;
    Animation fromtop,fromBottom;
    LinearLayout textSplash,textHome,home;
    Spinner partner;
    TextView splashtext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        cloveranim = AnimationUtils.loadAnimation(this,R.anim.cloveranim);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this,R.anim.fromdown);
        textSplash = findViewById(R.id.textsplash);
        splashtext = findViewById(R.id.splashUser);
        textHome = findViewById(R.id.textOptions);
        home = findViewById(R.id.menus);
        bgApp = findViewById(R.id.bgapp);
        bganim = AnimationUtils.loadAnimation(this,R.anim.bganim);
        menu = findViewById(R.id.menupointbutton);
        kitchen = findViewById(R.id.kitchenButton);
        school = findViewById(R.id.schoolButton);
        inkind = findViewById(R.id.inkindButton);
        walkers = findViewById(R.id.walkersButton);
        partner = findViewById(R.id.spinnerpartner);
        clover = findViewById(R.id.clover);
        settings = findViewById(R.id.settingsButton);



        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goHome();
                        bar.stopLoader();

                    }
                }, 4000);

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });


        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        inkind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        walkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });
    }

    public void goHome(){
        bgApp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textSplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        textHome.setVisibility(View.VISIBLE);
        home.setVisibility(View.VISIBLE);
        textHome.startAnimation(fromBottom);
        home.startAnimation(fromBottom);
    }

    private void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.activity_home_drawer);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent);
                customType(HomeActivity.this,"fadein-to-fadeout");
                return true;
            case R.id.item2:
            case R.id.item3:
                Toasty.info(HomeActivity.this, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
                return true;
            case R.id.item4:
                logout();
                return true;
            default:
                return false;
        }
    }

    private void logout(){
        Call<UserLog> call = ApiClient.getInstance().getApi().login();
        call.enqueue(new Callback<UserLog>() {
            @Override
            public void onResponse(Call<UserLog> call, Response<UserLog> response) {
                if (response.isSuccessful()) {
                    Toasty.success(HomeActivity.this, "Adios!", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    customType(HomeActivity.this, "fadein-to-fadeout");
                }else{
                    Toast.makeText(HomeActivity.this, "Cierre de sesion fallida", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLog> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Fallo al cerrar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
