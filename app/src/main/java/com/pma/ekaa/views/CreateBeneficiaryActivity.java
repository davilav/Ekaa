package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Beneficiary;
import com.pma.ekaa.models.RegisterBeneficiary;
import com.pma.ekaa.models.User;

import org.w3c.dom.Text;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class CreateBeneficiaryActivity extends AppCompatActivity {

    TextView age;
    EditText name,secondname,lastname,surname;
    EditText nationality,documenttype,document;
    EditText pregnant,gender,phone,info,family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beneficiary);

        name = findViewById(R.id.namebeneficiary);
        secondname = findViewById(R.id.seconenamebeneficiary);
        lastname = findViewById(R.id.lastnamebeneficiary);
        surname = findViewById(R.id.surnamebeneficiary);
        nationality = findViewById(R.id.nationalitybeneficiary);
        documenttype = findViewById(R.id.documentTypebeneficiary);
        document = findViewById(R.id.documentbeneficiary);
        pregnant = findViewById(R.id.pregnantbeneficiary);
        gender = findViewById(R.id.genderbeneficiary);
        phone = findViewById(R.id.phonebeneficiary);
        info = findViewById(R.id.informationbeneficiary);
        family = findViewById(R.id.familybeneficiary);

        age = findViewById(R.id.birthdatebeneficiary);

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se crea un nuevo calendario para obtener la fecha actual
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                //se crea la ventana del calendario desde donde se elegira la fecha
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(),datePickerListener, mYear, mMonth, mDay);
                dateDialog.show();
            }
        });
    }
    //se crea un listener para interactuar con el calendario
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        //al pasar la fecha y dar ok se setea la fecha en el textview
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            age.setText(year + "/" + month + "/" + day);
        }
    };


    void registerBeneficiary(){

        final String Name = name.getText().toString();
        final String secondName = secondname.getText().toString();
        final String lastName = lastname.getText().toString();
        final String surName = surname.getText().toString();
        final int Nationality = 1;
        final int documentType = 1;
        final String Document = document.getText().toString();
        final String Pregnant = pregnant.getText().toString();
        final int Gender = 1;
        final String Info = info.getText().toString();
        final String Phone = phone.getText().toString();
        final String Age = age.getText().toString();
        final String Family = family.getText().toString();
        final int Agreement = 1;
        final String Geolocation = "NA";

        RegisterBeneficiary registerBeneficiary = new RegisterBeneficiary(Nationality,documentType,Gender,Agreement,Geolocation,Name,secondName,lastName,surName,Document,Age,Pregnant,Phone,Info,Family);


        retrofit2.Call<Beneficiary> call = ApiClient.getInstance().getApi().registerBeneficiary(registerBeneficiary);
        call.enqueue(new Callback<Beneficiary>() {
            @Override
            public void onResponse(retrofit2.Call<Beneficiary> call, Response<Beneficiary> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateBeneficiaryActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateBeneficiaryActivity.this, HomeActivity.class);
                    startActivity(intent);
                    customType(CreateBeneficiaryActivity.this, "fadein-to-fadeout");
                    //token =  response.body().getToken();

                }
                else {
                    Toast.makeText(CreateBeneficiaryActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Beneficiary> call, Throwable t) {
                Toast.makeText(CreateBeneficiaryActivity.this, "Registro de ususario fallido", Toast.LENGTH_SHORT).show();
            }

        });
    }

}

