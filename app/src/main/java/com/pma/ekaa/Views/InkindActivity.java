package com.pma.ekaa.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pma.ekaa.R;
import com.pma.ekaa.adapters.ItemAdapter;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.BeneficiaryArray;
import com.pma.ekaa.models.RequestUser;
import com.pma.ekaa.models.Result;
import com.pma.ekaa.models.Utils;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class InkindActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Result> beneficiaries;
    private static int countPage = 1;
    private final ArrayList<Result> itemList = new ArrayList<>();



    ImageView nextpage,previouspage;
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
        setContentView(R.layout.activity_inkind);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        nextpage = findViewById(R.id.nextArrowButton);
        previouspage = findViewById(R.id.previousArrowButton);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Result");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() < 2){
                    listBeneficiary(query,countPage);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listBeneficiary(s,countPage);
                return false;
            }
        });


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
                finish();
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
        listBeneficiary("",countPage);

    }


    private void mostrarAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(InkindActivity.this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_alert_message,null);

        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button btnAceptar = view.findViewById(R.id.ButtonAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestUser obj = new RequestUser();
                obj.setToken(token);
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(InkindActivity.this, CreateBeneficiaryActivity.class);
                startActivity(intent);
                customType(InkindActivity.this,"fadein-to-fadeout");
            }
        });

        Button btnCancelar = view.findViewById(R.id.buttonCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(InkindActivity.this,CreateBeneficiaryActivity.class);
                startActivity(intent);
            }
        });


    }

    public void listBeneficiary(final String keyword, int page){

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countPage+=1;
                listBeneficiary("",countPage);
                Toasty.success(InkindActivity.this, "Pagina: "+countPage, Toast.LENGTH_SHORT, true).show();

            }
        });

        previouspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countPage-=1;
                listBeneficiary("",countPage);
                Toasty.success(InkindActivity.this, "Pagina: "+countPage, Toast.LENGTH_SHORT, true).show();
            }
        });

        retrofit2.Call<BeneficiaryArray> call = ApiClient.getInstance().getApi().listBeneficiary("Token "+token,keyword,page);
        call.enqueue(new Callback<BeneficiaryArray>() {
            @Override
            public void onResponse(Call<BeneficiaryArray> call, Response<BeneficiaryArray> response) {
                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.INVISIBLE);
                    beneficiaries =  response.body().getResults();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),beneficiaries));

                }
                else {
                    Toasty.error(InkindActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT, true).show();
                }
            }
            @Override
            public void onFailure(Call<BeneficiaryArray> call, Throwable t) {
                Toasty.warning(InkindActivity.this, "Fallo la conexion con el servidor", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}
