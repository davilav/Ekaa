package com.pma.ekaa.Views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Result;

import static maes.tech.intentanim.CustomIntent.customType;

public class BeneficiaryActivity extends AppCompatActivity {

    public static String OBJECT_BENEFICIARIES = "object_beneficiaries";

    ImageView back;
    TextView name,familyCode,documentType,documentNumber;
    TextView nation,gender,phone,registration,info,birthdate,completeName,modality;
    Button editBeneficiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

        Intent intentExtras = getIntent();
        String object = intentExtras.getStringExtra(OBJECT_BENEFICIARIES);

        final Result result = new Gson().fromJson(object, Result.class);

        completeBeneficiary(result);

        editBeneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String object = new Gson().toJson(result);
                Intent intent = new Intent(BeneficiaryActivity.this, EditBeneficiaryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(BeneficiaryActivity.OBJECT_BENEFICIARIES, object);
                startActivity(intent);
                customType(BeneficiaryActivity.this,"fadein-to-fadeout");
            }
        });


        back = findViewById(R.id.backKitchenbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void initViews(){


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
        modality = findViewById(R.id.tv_modality);
        editBeneficiary = findViewById(R.id.editKitchenbutton);


    }

    public void completeBeneficiary(Result result){

        name.setText(result.getFirstName()+" "+ result.getSurname());
        birthdate.setText(result.getBirthDate());
        completeName.setText(result.getFirstName()+" "+ result.getSecondName()+" "+ result.getSurname()+" "+ result.getSecondSurname());
        documentNumber.setText(result.getDocument());
        phone.setText(result.getPhone());
        registration.setText(result.getRegistrationDate());
        info.setText(result.getAditionalInformation());
        modality.setText("");




    }
}
