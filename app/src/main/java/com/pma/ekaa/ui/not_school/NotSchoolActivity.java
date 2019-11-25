package com.pma.ekaa.ui.not_school;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.adapters.ItemAdapter;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.not_school.presenter.NotSchoolPresenter;
import com.pma.ekaa.ui.not_school.presenter.NotSchoolPresenterImpl;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class NotSchoolActivity extends BaseActivity implements NotSchoolView, View.OnClickListener, ItemAdapter.onListenerAdapter{

    public static String OPTION_ACTION = "option_action";
    public static String OPTION_MODALITY = "option_modality";
    public static String INSTITUTION_ID = "institution_id";

    public final static int KITCHEN = 0;
    public final static int WALKERS = 1;
    public final static int INKIND = 2;

    private NotSchoolPresenter presenter;
    private int optionAction;
    private int institutionID;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Result> beneficiaries;
    private static int countPage = 1;
    private final ArrayList<Result> itemList = new ArrayList<>();
    private ArrayList<Modality>  modalities;
    private ImageView nextpage,previouspage;
    private ConstraintLayout loading;
    private ImageView back,info;
    private FloatingActionButton floatingActionButton;
    private TextView titleToolbar;
    private SearchView searchView;
    private Dialog dialog;

    Double Longitude = Utils.getInstance().getGeolocation().getLongitude();
    Double Latitude = Utils.getInstance().getGeolocation().getLatitude();
    private String agreement = "{\"agreement1\":\"AG1\",\"agreement2\":\"AG2\",\"agreement3\":\"AG3\",\"agreement4\":\"AG4\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_school);
        if(savedInstanceState != null){
            optionAction = savedInstanceState.getInt(OPTION_ACTION);
            modalities = new Gson().fromJson(savedInstanceState.getString(OPTION_MODALITY), new TypeToken<List<Modality>>(){}.getType());
            institutionID = savedInstanceState.getInt(INSTITUTION_ID);
        } else {
            optionAction = getIntent().getIntExtra(OPTION_ACTION, -1);
            modalities = new Gson().fromJson(getIntent().getStringExtra(OPTION_MODALITY), new TypeToken<List<Modality>>(){}.getType());
            institutionID = getIntent().getIntExtra(INSTITUTION_ID, -1);
        }

        presenter = new NotSchoolPresenterImpl(this);

        nextpage = findViewById(R.id.nextArrowButton);
        previouspage = findViewById(R.id.previousArrowButton);
        searchView = findViewById(R.id.searchView);
        loading = findViewById(R.id.progressBar);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        back = findViewById(R.id.backButton);
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
        itemAdapter = new ItemAdapter(getApplicationContext(), itemList, modalities, institutionID, this);
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
        switch (optionAction){
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

        final ArrayList<CheckBox> listCheckBox = new ArrayList<CheckBox>();
        listCheckBox.add((CheckBox) view.findViewById(R.id.wfpCheckBox));
        listCheckBox.add((CheckBox) view.findViewById(R.id.humanitarianCheckBox));
        listCheckBox.add((CheckBox) view.findViewById(R.id.privateCheckBox));
        listCheckBox.add((CheckBox) view.findViewById(R.id.governmentCheckBox));

        final ArrayList<String> keys = new ArrayList<String>();
        keys.add("AG1");
        keys.add("AG2");
        keys.add("AG3");
        keys.add("AG4");



        Button btnAceptar = view.findViewById(R.id.ButtonAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String option = setOptionAgreement(listCheckBox, keys);
                startBeneficiary(optionAction, BeneficiaryActivity.CREATE, option,null);
            }
        });

        Button btnCancelar = view.findViewById(R.id.buttonCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String option = setOptionAgreement(listCheckBox, keys);
                startBeneficiary(optionAction, BeneficiaryActivity.CREATE, option, null);
            }
        });


    }

    private String setOptionAgreement(ArrayList<CheckBox> listCheckBox, ArrayList<String> keys) {
        String result = "";
        for(int cont = 0; cont < listCheckBox.size(); cont++ ){
            if(listCheckBox.get(cont).isChecked()){
                result = agreement.replace(keys.get(cont), "1");
            } else {
                result =agreement.replace(keys.get(cont), "0");
            }
            agreement = result;
        }
        return agreement;
    }

    public void listBeneficiary(final String keyword, int page){
        showLoading();
        if(countPage == 1){
            previouspage.setVisibility(View.INVISIBLE);
        }else{
            previouspage.setVisibility(View.VISIBLE);
        }

        presenter.getListBeneficiary(keyword,page);

    }

    @Override
    public void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray) {
        hideLoading();
        beneficiaries =  beneficiaryArray.getResults();
        recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),beneficiaries, modalities, institutionID, this));
    }

    @Override
    public void setRegisterAttendanceSuccess() {
        hideLoading();
        dialog.dismiss();
        Toasty.success(getApplicationContext(), "Atencion registrada exitosamente", Toast.LENGTH_SHORT, true).show();
        listBeneficiary("",countPage);
    }

    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.error(getApplicationContext(), msg, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void registerAttendance(Dialog myDialog, int institution, int userID, int person, int modality){
        showLoading();
        dialog = myDialog;
        presenter.setRegisterAttendance(Longitude, Latitude, institution, userID, person, modality);
    }

    @Override
    public void showBeneficiary(Result beneficiary) {
        startBeneficiary(optionAction, BeneficiaryActivity.SHOW, "", beneficiary);
    }

    private void startBeneficiary(int option, int item, String agreement, Result beneficiary) {
        Intent intent = new Intent(this, BeneficiaryActivity.class);
        intent.putExtra(OPTION_ACTION, option);
        intent.putExtra(BeneficiaryActivity.SELECTED_ITEM, item);
        if(beneficiary != null) {
            intent.putExtra(BeneficiaryActivity.OBJECT_BENEFICIARIES, new Gson().toJson(beneficiary));
        } else {
            intent.putExtra(BeneficiaryActivity.OBJECT_BENEFICIARIES, "");
        }
        if(agreement != null) {
            intent.putExtra(BeneficiaryActivity.OPTION_AGREEMENT, agreement);
        } else {
            intent.putExtra(BeneficiaryActivity.OPTION_AGREEMENT, "");
        }

        startActivity(intent);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemAdapter.notifyDataSetChanged();
        listBeneficiary("",countPage);
    }
}
