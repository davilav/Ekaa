package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.pma.ekaa.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class CreateBeneficiaryActivity extends AppCompatActivity {

    TextView age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beneficiary);

        age = findViewById(R.id.age);

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

}

