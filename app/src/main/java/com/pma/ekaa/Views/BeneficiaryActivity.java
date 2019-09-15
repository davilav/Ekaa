package com.pma.ekaa.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.models.Beneficiary;

import static maes.tech.intentanim.CustomIntent.customType;

public class BeneficiaryActivity extends AppCompatActivity {

    public static String OBJECT_BENEFICIARIES = "object_beneficiaries";

    ImageView back;
    TextView name,familyCode,documentType,documentNumber;
    TextView nation,gender,phone,registration,info,birthdate,completeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        name = findViewById(R.id.tv_name);
        familyCode = findViewById(R.id.tv_family);
        documentType = findViewById(R.id.tv_document);
        documentNumber = findViewById(R.id.tv_number_document);
        nation = findViewById(R.id.tv_nation);
        gender = findViewById(R.id.tv_gender);
        phone = findViewById(R.id.tv_phone);
        registration = findViewById(R.id.tv_date);
        info = findViewById(R.id.tv_info);
        birthdate = findViewById(R.id.tv_birthdate);
        completeName = findViewById(R.id.tv_complete_name);

        Intent intentExtras = getIntent();
        String object = intentExtras.getStringExtra(OBJECT_BENEFICIARIES);

        Beneficiary beneficiary = new Gson().fromJson(object, Beneficiary.class);

        completeBeneficiary(beneficiary);


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

    public void completeBeneficiary(Beneficiary beneficiary){

        name.setText(beneficiary.getFirst_name()+" "+ beneficiary.getSurname());
        birthdate.setText(beneficiary.getBirth_date());
        completeName.setText(beneficiary.getFirst_name()+" "+beneficiary.getSecond_name()+" "+beneficiary.getSurname()+" "+beneficiary.getSecond_surname());
        documentNumber.setText(beneficiary.getDocument());
        phone.setText(beneficiary.getPhone());
        registration.setText(beneficiary.getRegistration_date());
        info.setText(beneficiary.getAditional_information());




    }
}
