package com.pma.ekaa.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.adapters.ItemAdapter;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Beneficiary;
import com.pma.ekaa.models.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.pma.ekaa.views.BeneficiaryActivity.OBJECT_BENEFICIARIES;
import static maes.tech.intentanim.CustomIntent.customType;

public class KitchenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Beneficiary> beneficiaries;
    private final ArrayList<Beneficiary> itemList = new ArrayList<>();


    ProgressBar progressBar;
    ImageView back,info;
    FloatingActionButton floatingActionButton;
    CheckBox attendance;
    Dialog myDialog;
    String token = Utils.getInstance().getObj().getToken();
    int contador = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        myDialog = new Dialog(this);
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
        attendance = findViewById(R.id.Atencion);

        recyclerView = findViewById(R.id.recycler_view);

        itemAdapter = new ItemAdapter(getApplicationContext(),itemList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(itemAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        listBeneficiary ();

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



    public void listBeneficiary(){


        retrofit2.Call<List<Beneficiary>> call = ApiClient.getInstance().getApi().listBeneficiary("Token "+token);
        call.enqueue(new Callback<List<Beneficiary>>() {
            @Override
            public void onResponse(Call<List<Beneficiary>> call, Response<List<Beneficiary>> response) {
                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.INVISIBLE);
                    beneficiaries = response.body();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),beneficiaries));


                }
                else {
                    Toasty.error(KitchenActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<List<Beneficiary>> call, Throwable t) {
                Toasty.warning(KitchenActivity.this, "Fallo la conexion con el servidor", Toast.LENGTH_SHORT, true).show();
            }
        });
    }




}
