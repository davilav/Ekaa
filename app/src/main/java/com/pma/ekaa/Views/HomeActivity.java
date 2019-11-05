package com.pma.ekaa.Views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.AuthUser;
import com.pma.ekaa.models.BeneficiaryArray;
import com.pma.ekaa.models.DataUser;
import com.pma.ekaa.models.InstitutionByPartner;
import com.pma.ekaa.models.RequestUser;
import com.pma.ekaa.models.Result;
import com.pma.ekaa.models.UserLog;
import com.pma.ekaa.models.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import okhttp3.ResponseBody;
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
    Spinner partner,location;
    TextView splashtext,userText,emailtext;

    int locationEmpty = 0;
    ArrayList typesSpinner1 = new ArrayList();
    ArrayList typesSpinner2 = new ArrayList();

    ArrayList<InstitutionByPartner> dataInstitutionbypartner = new ArrayList();
    HashMap<Integer,String> mapLocation = new HashMap();
    HashMap<Integer,String> mapInstitution = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();
        getDataInstitutionByPartner();
        capturateUserData();

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
                Intent intent = new Intent(HomeActivity.this, SchoolActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, InkindActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, WalkersActivity.class);
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

        partner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                int key = getKey(selectedItemText);




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                int key = getKey(selectedItemText);

                if(key == locationEmpty){
                    partner.setEnabled(false);

                }else {
                    partner.setEnabled(true);
                    setSpinnerInstitution(key);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private int getKey(String selectedItemText) {
        int key = 0;
        for(Map.Entry<Integer,String> map : mapLocation.entrySet() ) {
            if(map.getValue().equals(selectedItemText)){
                key = map.getKey();
                break;
            }
        }

        return key;
    }


    public void initViews(){

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
        location = findViewById(R.id.spinnergeolocation);
        clover = findViewById(R.id.clover);
        settings = findViewById(R.id.settingsButton);
        partner = findViewById(R.id.spinnerpartner);
        location = findViewById(R.id.spinnergeolocation);
        userText = findViewById(R.id.userText);
        emailtext = findViewById(R.id.textemailhome);
        partner.setEnabled(false);


    }

    public void getDataInstitutionByPartner(){

        retrofit2.Call<ArrayList<InstitutionByPartner>>  call = ApiClient.getInstance().getApi().getInstitutions("Token "+token);
        call.enqueue(new Callback<ArrayList<InstitutionByPartner>> () {


            @Override
            public void onResponse(Call<ArrayList<InstitutionByPartner>> call, Response<ArrayList<InstitutionByPartner>> response) {

                dataInstitutionbypartner = response.body();
                setSpinnerLocation();


            }

            @Override
            public void onFailure(Call<ArrayList<InstitutionByPartner>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Fallo al cerrar sesion", Toast.LENGTH_SHORT).show();

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
                        goHome();
                        bar.stopLoader();

                    }
                }, 4000);

            }
        });

    }

    private void setSpinnerLocation() {

        mapLocation.put(0,"");

        for(int position = 0; position < dataInstitutionbypartner.size(); position++){
            mapLocation.put(dataInstitutionbypartner.get(position).getGeolocation().getId(), dataInstitutionbypartner.get(position).getGeolocation().getName());
        }


        typesSpinner1 = new ArrayList(mapLocation.values());


        ArrayAdapter comboAdapterLocation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,typesSpinner1);

        location.setAdapter(comboAdapterLocation);

    }


    private void setSpinnerInstitution(int keyLocation){
        mapInstitution.clear();

        for(int position = 0; position < dataInstitutionbypartner.size(); position++){
            if(keyLocation == dataInstitutionbypartner.get(position).getGeolocation().getId()){

                mapInstitution.put(dataInstitutionbypartner.get(position).getId(), dataInstitutionbypartner.get(position).getName());

            }

        }

        typesSpinner2 = new ArrayList(mapInstitution.values());

        ArrayAdapter comboAdapterInstitution = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,typesSpinner2);
        partner.setAdapter(comboAdapterInstitution);

    }

    public void capturateUserData(){
        retrofit2.Call<DataUser> call = ApiClient.getInstance().getApi().getDataUser("Token "+token);
        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {

                splashtext.setText(response.body().getUsername());
                userText.setText(response.body().getUsername());
                emailtext.setText(response.body().getEmail());

                AuthUser obj = new AuthUser();
                obj.setPk(response.body().getPk());
                Utils.getInstance().setObject(obj);

            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                int a = 0;
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
    public void onBackPressed() {

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
                    finish();
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
