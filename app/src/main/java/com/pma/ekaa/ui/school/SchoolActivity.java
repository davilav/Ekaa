package com.pma.ekaa.ui.school;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.student.StudentActivity;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.adapters.ItemAdapter;
import com.pma.ekaa.ui.attendance.AttendanceDetailActivity;
import com.pma.ekaa.ui.school.presenter.SchoolPresenter;
import com.pma.ekaa.ui.school.presenter.SchoolPresenterImpl;
import com.pma.ekaa.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SchoolActivity extends BaseActivity implements SchoolView, View.OnClickListener, ItemAdapter.onListenerAdapter {

    public static final String OPTION_MODALITY = "option_modality";
    public static final String INSTITUTION_ID = "institution_id";

    private ConstraintLayout loading;
    private ItemAdapter itemAdapter;
    private static int countPage = 1;
    private final ArrayList<Result> itemList = new ArrayList<>();
    private ArrayList<Modality>  modalities;
    private SearchView searchView;
    private RecyclerView recyclerView;

    private int institutionID;
    private ImageView previouspage;
    private ImageView nextpage;
    private ImageView back;
    private Dialog attendanceDialog;
    private Result selectBeneficiary;

    private int groupID = 0;

    private SchoolPresenter presenter;


    Integer userID = Utils.getInstance().getDataUser().getUserId();
    Double Longitude = Utils.getInstance().getGeolocation().getLongitude();
    Double Latitude = Utils.getInstance().getGeolocation().getLatitude();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        if(savedInstanceState != null){
            modalities = new Gson().fromJson(savedInstanceState.getString(OPTION_MODALITY), new TypeToken<List<Modality>>(){}.getType());
            institutionID = savedInstanceState.getInt(INSTITUTION_ID);
        } else {
            modalities = new Gson().fromJson(getIntent().getStringExtra(OPTION_MODALITY), new TypeToken<List<Modality>>(){}.getType());
            institutionID = getIntent().getIntExtra(INSTITUTION_ID, -1);
        }

        presenter = new SchoolPresenterImpl(this);

        initViews();

        showLoading();

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



    private void searchManager() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.searchresult));
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

    public void listBeneficiary(final String keyword, int page){
        showLoading();
        if(countPage == 1){
            previouspage.setVisibility(View.INVISIBLE);
        }else{
            previouspage.setVisibility(View.VISIBLE);
        }
        presenter.getListBeneficiary(keyword,page, institutionID, groupID);

    }

    public void initViews(){

        loading = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycler_view);
        previouspage = findViewById(R.id.previousArrowButton);
        nextpage = findViewById(R.id.nextArrowButton);
        back = findViewById(R.id.backButton);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(this);
        nextpage.setOnClickListener(this);
        previouspage.setOnClickListener(this);
        back.setOnClickListener(this);

    }


    private void showAttendanceDialog(final Result beneficiary, ArrayList<AttendanceToday> response) {

        attendanceDialog = new Dialog(this);
        attendanceDialog.setContentView(R.layout.beneficiary_popup);

        final CheckBox AM = attendanceDialog.findViewById(R.id.AM);
        final CheckBox PM = attendanceDialog.findViewById(R.id.PM);
        final CheckBox lunch = attendanceDialog.findViewById(R.id.lunch);
        TextView txtclose = attendanceDialog.findViewById(R.id.txtclose);
        TextView kitchenName = attendanceDialog.findViewById(R.id.kitchen_name);
        TextView firstComplement = attendanceDialog.findViewById(R.id.first_complement);
        TextView secondComplement = attendanceDialog.findViewById(R.id.second_complement);
        TextView thirdComplement = attendanceDialog.findViewById(R.id.third_complement);
        TextView detailAttendance = attendanceDialog.findViewById(R.id.detail);
        final Button bar = attendanceDialog.findViewById(R.id.btnfollow);

        //Se deberia implementar un recycler view para listar las opciones

        firstComplement.setText(modalities.get(0).getName());
        secondComplement.setText(modalities.get(1).getName());
        thirdComplement.setText(modalities.get(2).getName());

        ((LinearLayout) attendanceDialog.findViewById(R.id.color_first)).setBackgroundColor(Color.parseColor(modalities.get(0).getColor()));
        ((LinearLayout) attendanceDialog.findViewById(R.id.color_second)).setBackgroundColor(Color.parseColor(modalities.get(1).getColor()));
        ((LinearLayout) attendanceDialog.findViewById(R.id.color_third)).setBackgroundColor(Color.parseColor(modalities.get(2).getColor()));

        txtclose.setText("X");
        kitchenName.setText(beneficiary.getFirst_name()+" "+ beneficiary.getSurname());

        for(int cont = 0; cont < response.size(); cont++){
            if(response.get(cont).getModality_id() == 1){
                AM.setEnabled(false);
            } else if(response.get(cont).getModality_id() == 2){
                lunch.setEnabled(false);
            } else {
                PM.setEnabled(false);
            }
        }

        AM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    PM.setChecked(false);
                    lunch.setChecked(false);
                }
            }
        });

        PM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    AM.setChecked(false);
                    lunch.setChecked(false);
                }
            }
        });

        lunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    PM.setChecked(false);
                    AM.setChecked(false);
                }
            }
        });

        detailAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAttendanceDetail(beneficiary);
            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendanceDialog.dismiss();
            }
        });

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int modality = 0;
                if(AM.isChecked()){
                    modality = 1;
                }else if (lunch.isChecked()){
                    modality = 2;
                }else if(PM.isChecked()){
                    modality = 3;
                }

                registerAttendance(institutionID, beneficiary.getId(), userID, modality);
            }
        });

        attendanceDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        attendanceDialog.show();
    }

    private void showAttendanceDetail(Result beneficiary) {
        Intent intent = new Intent(this, AttendanceDetailActivity.class);
        intent.putExtra(AttendanceDetailActivity.USER_DETAIL, new Gson().toJson(beneficiary));
        startActivity(intent);
    }

    private void registerAttendance(int institution, int userID, int person, int modality){
        showLoading();
        presenter.setRegisterAttendance(Longitude, Latitude, institution, userID, person, modality);
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
    public void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray) {
        hideLoading();
        List<Result> student = beneficiaryArray.getResults();
        recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), student, modalities, institutionID, this));

        try {
            if(beneficiaryArray.getNext() == null){
                nextpage.setVisibility(View.INVISIBLE);
            }
            else{
                nextpage.setVisibility(View.VISIBLE);
            }
        }catch (Exception ex){
        }
    }

    @Override
    public void attendanceTodaySuccess(ArrayList<AttendanceToday> response) {
            hideLoading();
            showAttendanceDialog(selectBeneficiary, response);
        }

    @Override
    public void setRegisterAttendanceSuccess() {
        hideLoading();
        attendanceDialog.dismiss();
        Toasty.success(getApplicationContext(), getResources().getString(R.string.attendancesuccess), Toast.LENGTH_SHORT, true).show();
        listBeneficiary("",countPage);
    }

    @Override
    public void createBeneficiarySuccess() {
        hideLoading();
        Toasty.success(this, getResources().getString(R.string.beneficiarysucces), Toast.LENGTH_SHORT, true).show();
        finish();
    }

    @Override
    public void updateBeneficiarySuccess() {
        hideLoading();
        Toasty.success(this, getResources().getString(R.string.beneficiaryupdate), Toast.LENGTH_SHORT, true).show();
        finish();
    }


    @Override
    public void responseError(String msg) {
        hideLoading();
        Toasty.warning(SchoolActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.floatingActionButton:{
                startStudent(StudentActivity.CREATE, null);
                break;
            }
            case R.id.backButton:{
                finish();
                break;
            }
            case R.id.nextArrowButton: {
                countPage+=1;
                listBeneficiary("",countPage);
                Toasty.success(this, getResources().getString(R.string.page)+countPage, Toast.LENGTH_SHORT, true).show();
                break;
            }
            case R.id.previousArrowButton:
                countPage-=1;
                listBeneficiary("",countPage);
                Toasty.success(this, getResources().getString(R.string.page)+countPage, Toast.LENGTH_SHORT, true).show();
                break;
            default:

                break;
        }
    }

    private void startStudent(int item, Result beneficiary) {
        Intent intent = new Intent(this, StudentActivity.class);
        intent.putExtra(StudentActivity.SELECTED_ITEM, item);
        intent.putExtra(StudentActivity.INSTITUTION_ID, institutionID);
        intent.putExtra(StudentActivity.GROUP_ID, groupID);
        if(beneficiary != null) {
            intent.putExtra(StudentActivity.OBJECT_BENEFICIARIES, new Gson().toJson(beneficiary));
        } else {
            intent.putExtra(StudentActivity.OBJECT_BENEFICIARIES, "");
        }
        startActivity(intent);
    }

    @Override
    public void showBeneficiary(Result beneficiary) {
        startStudent(StudentActivity.SHOW, beneficiary);
    }

    @Override
    public void attendanceToday(Result beneficiary) {
        showLoading();
        selectBeneficiary = beneficiary;
        presenter.getAttendanceToday(beneficiary.getId());

    }



    @Override
    protected void onResume() {
        super.onResume();
        itemAdapter.notifyDataSetChanged();
        listBeneficiary("",countPage);
    }

}
