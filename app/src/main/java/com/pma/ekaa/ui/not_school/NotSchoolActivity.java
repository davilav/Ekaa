package com.pma.ekaa.ui.not_school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class NotSchoolActivity extends BaseActivity implements View.OnClickListener {

    public static String SELECTED_ITEM = "select_item";

    public final static int KITCHEN = 0;
    public final static int WALKERS = 1;
    public final static int INKIND = 2;

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
    private Dialog myDialog = new Dialog(this);
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

        nextpage = findViewById(R.id.nextArrowButton);
        previouspage = findViewById(R.id.previousArrowButton);
        searchView = findViewById(R.id.searchView);
        progressBar = findViewById(R.id.progressBar);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        back = findViewById(R.id.backButton);
        attendance = findViewById(R.id.Atencion);
        recyclerView = findViewById(R.id.recycler_view);

        floatingActionButton.setOnClickListener(this);
        back.setOnClickListener(this);
        nextpage.setOnClickListener(this);
        previouspage.setOnClickListener(this);

        searchManager();

    }

    private void searchManager() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Result");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() < 2){
                    //listBeneficiary(query,countPage);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //listBeneficiary(s,countPage);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.floatingActionButton:{
                //mostrarAlert();
                break;
            }
            case R.id.backButton:{
                finish();
                break;
            }
            case R.id.nextArrowButton: {
                countPage+=1;
                //listBeneficiary("",countPage);
                Toasty.success(this, "Pagina: "+countPage, Toast.LENGTH_SHORT, true).show();
                break;
            }
            case R.id.previousArrowButton:
                countPage-=1;
                //listBeneficiary("",countPage);
                Toasty.success(this, "Pagina: "+countPage, Toast.LENGTH_SHORT, true).show();
                break;
        }
    }
}
