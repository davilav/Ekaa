package com.pma.ekaa.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.models.Result;

import static com.pma.ekaa.Views.BeneficiaryActivity.OBJECT_BENEFICIARIES;

public class EditBeneficiaryActivity extends AppCompatActivity {


    public static String OBJECT_BENEFICIARIES = "object_beneficiaries";

    ImageView back;
    ImageView namecomplete,date,nationality,idnumber,sex,cell;
    TextView name,familyCode,documentType,documentNumber;
    TextView nation,gender,phone,registration,info,birthdate,completeName,modality;
    Button editBeneficiary;
    AlertDialog alertDialog;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_beneficiary);

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
        namecomplete = findViewById(R.id.editname);
        editText = new EditText(this);


        alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Editar campo");
        alertDialog.setView(editText);


        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        namecomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });





        Intent intentExtras = getIntent();
        String object = intentExtras.getStringExtra(OBJECT_BENEFICIARIES);

        final Result result = new Gson().fromJson(object, Result.class);

        completeBeneficiary(result);


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
