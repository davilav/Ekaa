package com.pma.ekaa.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Connection;
import com.pma.ekaa.utils.Utils;

public class ConnectionActivity extends AppCompatActivity implements View.OnClickListener{

    Button central,node;
    ConstraintLayout animation,content;
    String ipHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        initViews();

    }

    public void initViews() {

        central = findViewById(R.id.centralServer);
        node = findViewById(R.id.nodeServer);
        animation = findViewById(R.id.animationLayout);
        content = findViewById(R.id.contentLayout);

        central.setOnClickListener(this);
        node.setOnClickListener(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //animation.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
            }
        },7000);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.centralServer:
                ipHost = "http://200.91.218.166:8000/";
                goToSplash();
                break;

            case R.id.nodeServer:
                ipHost = "http://192.168.1.7:8000/";
                goToSplash();
                break;
        }
    }

    private void goToSplash() {

        Connection conn = new Connection();
        conn.setIpAddress(ipHost);
        Utils.getInstance().setConnection(conn);
        Intent intent = new Intent(ConnectionActivity.this, SplashActivity.class);
        startActivity(intent);
    }

}
