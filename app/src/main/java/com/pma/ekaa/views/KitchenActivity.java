package com.pma.ekaa.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pma.ekaa.R;
import com.pma.ekaa.adapters.ItemAdapter;
import com.pma.ekaa.models.Beneficiary;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class KitchenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ItemAdapter itemAdapter;

    ImageView back;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlert();
            }
        });

        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KitchenActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Beneficiary> itemList = new ArrayList<>();

        fillDummyData(itemList);


        recyclerView = findViewById(R.id.recycler_view);

        itemAdapter = new ItemAdapter(itemList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void fillDummyData(ArrayList<Beneficiary> celebList) {
        Beneficiary celeb1 = new Beneficiary();
        celeb1.setFirst_name("David Avila");
        celeb1.setId("1000335648");
       // celeb1.setNationality(1);
        celeb1.setProfilePhotoLocation("");
        celeb1.setHousehold_code("A32424B");
        celebList.add(celeb1);

        Beneficiary celeb2 = new Beneficiary();
        celeb1.setFirst_name("Felipe Avila");
        celeb1.setId("10320335648");
       // celeb1.setNationality(1);
        celeb1.setProfilePhotoLocation("");
        celeb1.setHousehold_code("A33424B");
        celebList.add(celeb1);



    }

    private void mostrarAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(KitchenActivity.this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_alert_message,null);

        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptar = view.findViewById(R.id.ButtonAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KitchenActivity.this, CreateBeneficiaryActivity.class);
                startActivity(intent);
                customType(KitchenActivity.this,"fadein-to-fadeout");
            }
        });

        Button btnCancelar = view.findViewById(R.id.buttonCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(KitchenActivity.this,CreateBeneficiaryActivity.class);
                startActivity(intent);
            }
        });


    }
}