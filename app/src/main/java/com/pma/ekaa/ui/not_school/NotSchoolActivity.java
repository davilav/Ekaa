package com.pma.ekaa.ui.not_school;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pma.ekaa.R;
import com.pma.ekaa.Views.CreateBeneficiaryActivity;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.data.remote.ApiClient;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.adapters.ItemAdapter;
import com.pma.ekaa.ui.not_school.presenter.NotSchoolPresenter;
import com.pma.ekaa.ui.not_school.presenter.NotSchoolPresenterImpl;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class NotSchoolActivity extends BaseActivity implements NotSchoolView, View.OnClickListener {

    public static String SELECTED_ITEM = "select_item";

    public final static int KITCHEN = 0;
    public final static int WALKERS = 1;
    public final static int INKIND = 2;

    private NotSchoolPresenter presenter;
    private int selectItem;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Result> beneficiaries;
    private static int countPage = 1;
    private final ArrayList<Result> itemList = new ArrayList<>();
    private ImageView nextpage,previouspage;
    private ProgressBar progressBar;
    private ImageView back,info;
    private FloatingActionButton floatingActionButton;
    private CheckBox attendance;
    private TextView titleToolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_school);
        if(savedInstanceState != null){
            selectItem = savedInstanceState.getInt(SELECTED_ITEM);
        } else {
            selectItem = getIntent().getIntExtra(SELECTED_ITEM, -1);
        }

        presenter = new NotSchoolPresenterImpl(this);

        nextpage = findViewById(R.id.nextArrowButton);
        previouspage = findViewById(R.id.previousArrowButton);
        searchView = findViewById(R.id.searchView);
        progressBar = findViewById(R.id.progressBar);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        back = findViewById(R.id.backButton);
        attendance = findViewById(R.id.Atencion);
        recyclerView = findViewById(R.id.recycler_view);
        titleToolbar = findViewById(R.id.titleToolbar);

        floatingActionButton.setOnClickListener(this);
        back.setOnClickListener(this);
        nextpage.setOnClickListener(this);
        previouspage.setOnClickListener(this);

        setTitleToolbar();

        searchManager();

        setAdapterRecycler();

    }

    private void setAdapterRecycler() {
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

    private void setTitleToolbar() {
        switch (selectItem){
            case KITCHEN :
                titleToolbar.setText(getResources().getString(R.string.kitchens));
                break;
            case WALKERS:
                titleToolbar.setText(getResources().getString(R.string.walkers));
                break;
            case INKIND:
                titleToolbar.setText(getResources().getString(R.string.inkind));
                break;
        }
    }

    private void searchManager() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.floatingActionButton:{
                mostrarAlert();
                break;
            }
            case R.id.backButton:{
                finish();
                break;
            }
            case R.id.nextArrowButton: {
                countPage+=1;
                listBeneficiary("",countPage);
                Toasty.success(this, "Pagina: "+countPage, Toast.LENGTH_SHORT, true).show();
                break;
            }
            case R.id.previousArrowButton:
                countPage-=1;
                listBeneficiary("",countPage);
                Toasty.success(this, "Pagina: "+countPage, Toast.LENGTH_SHORT, true).show();
                break;
        }
    }

    private void mostrarAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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
                obj.setToken(Utils.getInstance().getObj().getToken());
                Utils.getInstance().setObj(obj);
                Intent intent = new Intent(getApplicationContext(), CreateBeneficiaryActivity.class);
                startActivity(intent);
                customType(getApplicationContext(),"fadein-to-fadeout");
            }
        });

        Button btnCancelar = view.findViewById(R.id.buttonCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(),CreateBeneficiaryActivity.class);
                startActivity(intent);
            }
        });


    }

    public void listBeneficiary(final String keyword, int page){

        if(countPage == 1){
            previouspage.setVisibility(View.INVISIBLE);
        }else{
            previouspage.setVisibility(View.VISIBLE);
        }

        presenter.getListBeneficiary(Utils.getInstance().getObj().getToken(),keyword,page);

    }

    @Override
    public void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray) {
        progressBar.setVisibility(View.INVISIBLE);
        beneficiaries =  beneficiaryArray.getResults();
        recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),beneficiaries));
    }

    @Override
    public void responseError(String msg) {
        Toasty.error(getApplicationContext(), msg, Toast.LENGTH_SHORT, true).show();
    }
}
