package com.pma.ekaa.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Connection;
import com.pma.ekaa.utils.Utils;

import es.dmoral.toasty.Toasty;

public class ConnectionActivity extends AppCompatActivity implements View.OnClickListener{

    Button central;
    Button node;
    ConstraintLayout animation;
    ConstraintLayout content;
    String ipHost;
    CheckBox checkBox;
    EditText editText;

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
        checkBox = findViewById(R.id.checkBox);
        editText = findViewById(R.id.editText);

        central.setOnClickListener(this);
        node.setOnClickListener(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                content.setVisibility(View.VISIBLE);
            }
        },7000);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if(state){
                    editText.setVisibility(View.VISIBLE);
                    node.setEnabled(true);
                }else{
                    editText.setVisibility(View.INVISIBLE);
                    node.setEnabled(false);
                }
            }
        });

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.centralServer:
                ipHost = "http://200.91.218.166:8000/";
                goToSplash();
                break;

            case R.id.nodeServer:

                if(editText.getText().toString().matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")){
                    ipHost = "http://"+editText.getText().toString()+":8000/";
                    goToSplash();
                } else {
                    Toasty.warning(this, "Por favor ingrese una dirección IP valida", Toast.LENGTH_SHORT).show();
                }
                break;
            default:

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
