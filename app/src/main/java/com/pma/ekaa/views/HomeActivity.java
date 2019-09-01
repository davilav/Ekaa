package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.UserLog;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView kitchen,school,inkind,walkers,cloud,url,settings,info,menu;

    Intent intent = getIntent();
    String token = intent.getStringExtra(LoginActivity.key);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





        menu = findViewById(R.id.menupointbutton);
        kitchen = findViewById(R.id.kitchenButton);
        school = findViewById(R.id.schoolButton);
        inkind = findViewById(R.id.inkindButton);
        walkers = findViewById(R.id.walkersButton);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });


        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);;
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                intent.putExtra("token",token);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        inkind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                intent.putExtra("token",token);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });

        walkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, KitchenActivity.class);
                startActivity(intent);
                intent.putExtra("token",token);
                customType(HomeActivity.this,"fadein-to-fadeout");
            }
        });
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
                Toasty.info(HomeActivity.this, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
                return true;
            case R.id.item2:
                Toasty.info(HomeActivity.this, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
                return true;
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
