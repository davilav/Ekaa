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

public class ConnectionActivity extends AppCompatActivity{

    RadioButton radioServerRemote,radioServerLocale;
    RadioGroup rd;
    EditText ipAddress;
    TextView selectText;
    Button next,save;
    String ipHost,ipConn;
    ConstraintLayout connection,animation,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        initViews();

    }

    public void initViews() {

        radioServerRemote = findViewById(R.id.remote);
        radioServerLocale = findViewById(R.id.local);
        rd = findViewById(R.id.radioGroupConnection);
        selectText = findViewById(R.id.selectText);
        ipAddress = findViewById(R.id.ipAdress);
        next = findViewById(R.id.nextButton);
        save = findViewById(R.id.save);
        connection = findViewById(R.id.connection);
        animation = findViewById(R.id.animationLayout);
        content = findViewById(R.id.contentLayout);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
            }
        },7000);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioServerRemote.isChecked()){
                    ipAddress.setText("200.91.218.166");
                    ipAddress.setFocusable(false);
                    ipHost = "http://200.91.218.166:8000/";
                } else if (radioServerLocale.isChecked()){
                    ipHost = "http://"+ipAddress.getText().toString()+":8000/";

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSplash();
            }
        });

    }

    private void goToSplash() {

        Connection conn = new Connection();
        conn.setIpAddress(ipHost);
        Utils.getInstance().setConnection(conn);
        Intent intent = new Intent(ConnectionActivity.this, SplashActivity.class);
        startActivity(intent);
    }

}
