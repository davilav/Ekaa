package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;

import com.pma.ekaa.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class BeneficiaryActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);

        back = findViewById(R.id.backKitchenbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeneficiaryActivity.this,KitchenActivity.class);
                startActivity(intent);
                customType(BeneficiaryActivity.this,"fadein-to-fadeout");
            }
        });
    }
}
