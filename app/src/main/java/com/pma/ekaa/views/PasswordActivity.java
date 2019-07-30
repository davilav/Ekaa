package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.pma.ekaa.R;

import github.ishaan.buttonprogressbar.ButtonProgressBar;

import static maes.tech.intentanim.CustomIntent.customType;

public class PasswordActivity extends AppCompatActivity {

    Button memberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        memberButton = findViewById(R.id.memberButton);

        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bar.stopLoader();
                        Intent intent = new Intent(PasswordActivity.this, HomeActivity.class);
                        startActivity(intent);
                        customType(PasswordActivity.this,"fadein-to-fadeout");
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
}
