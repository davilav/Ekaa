package com.pma.ekaa.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pma.ekaa.R;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Result;
import com.pma.ekaa.models.RegisterBeneficiary;
import com.pma.ekaa.models.Utils;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class CreateBeneficiaryActivity extends AppCompatActivity {



    Button registerButton;
    TextView age;
    EditText name,secondname,lastname,surname;
    EditText document;
    EditText pregnant,phone,info,family;
    Spinner nationality,documenttype,gender;
    int location,dType,sex;
    String[] genders,types,nations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beneficiary);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


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
        genders = getResources().getStringArray(R.array.gender);
        types = getResources().getStringArray(R.array.documentType);
        nations = getResources().getStringArray(R.array.nation);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        documenttype.setAdapter(adapter);
        documenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                switch (position) {

                    case 0:
                        break;

                    case 1:
                        dType = 1;

                        break;

                    case 2:
                        dType = 2;

                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        gender.setAdapter(adapter2);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                switch (position) {

                    case 0:
                        break;

                    case 1:
                        sex = 1;
                        break;

                    case 2:
                        sex = 2;
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        nationality.setAdapter(adapter3);
        nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }

                switch (position) {

                    case 0:
                        break;

                    case 1:
                        location = 1;
                        break;

                    case 2:
                        location = 1;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


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
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.show();
            }
        });

        final ButtonProgressBar bar = findViewById(R.id.btn_recovery);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        registerBeneficiary();
                        bar.stopLoader();
                    }
                }, 4000);

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
        final int Nationality = location;
        final int documentType = dType;
        final String Document = document.getText().toString();
        final String Pregnant = pregnant.getText().toString();
        final int Gender = sex;
        final String Info = info.getText().toString();
        final String Phone = phone.getText().toString();
        final String Age = age.getText().toString();
        final String Family = family.getText().toString();
        final int Agreement = 1;
        final String Geolocation = "1";
        String token = Utils.getInstance().getObj().getToken();



        if (TextUtils.isEmpty(Name)) {
            Toasty.warning(CreateBeneficiaryActivity.this, "Debes ingresar el nombre del beneficiario", Toast.LENGTH_SHORT, true).show();
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            Toasty.warning(CreateBeneficiaryActivity.this, "Debes ingresar el apellido del beneficiario", Toast.LENGTH_SHORT, true).show();
            return;
        }

        if (TextUtils.isEmpty(surName)) {
            Toasty.warning(CreateBeneficiaryActivity.this, "Debes ingresar el segundo apellido del beneficiario", Toast.LENGTH_SHORT, true).show();
            return;
        }

        if (TextUtils.isEmpty(Document)) {
            Toasty.warning(CreateBeneficiaryActivity.this, "Debes ingresar el documento del beneficiario", Toast.LENGTH_SHORT, true).show();
            return;
        }


        RegisterBeneficiary registerBeneficiary = new RegisterBeneficiary(Nationality,documentType,Gender,Agreement,Geolocation,Name,secondName,lastName,surName,Document,Age,Pregnant,Phone,Info,Family);

        retrofit2.Call<Result> call = ApiClient.getInstance().getApi().registerBeneficiary(registerBeneficiary,("Token "+token));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(retrofit2.Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Toasty.success(CreateBeneficiaryActivity.this, "Beneficiario creado exitosamente!", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(CreateBeneficiaryActivity.this, KitchenActivity.class);
                    startActivity(intent);
                    customType(CreateBeneficiaryActivity.this, "fadein-to-fadeout");

                }
                else {
                    Toasty.error(CreateBeneficiaryActivity.this, "Error al crear beneficiario", Toast.LENGTH_SHORT, true).show();

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toasty.warning(CreateBeneficiaryActivity.this, "Fallo al registrar beneficiario", Toast.LENGTH_SHORT, true).show();
            }

        });
    }

}

