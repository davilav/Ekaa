package com.pma.ekaa.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pma.ekaa.R;
import com.pma.ekaa.adapters.ItemAdapter;
import com.pma.ekaa.models.Beneficiary;

import java.util.ArrayList;

public class KitchenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ItemAdapter itemAdapter;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

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
        celeb1.setName("David Avila");
        celeb1.setID("1000335648");
        celeb1.setNation("Colombia");
        celeb1.setProfilePhotoLocation("");
        celeb1.setFamily("A32424B");
        celebList.add(celeb1);

        Beneficiary celeb2 = new Beneficiary();
        celeb2.setName("Hugo Sanchez");
        celeb2.setID("1000349348");
        celeb2.setNation("Colombia");
        celeb2.setProfilePhotoLocation("");
        celeb2.setFamily("A32424B");
        celebList.add(celeb2);

        Beneficiary celeb3 = new Beneficiary();
        celeb3.setName("Pedro Gomez");
        celeb3.setID("100434352");
        celeb3.setNation("Colombia");
        celeb3.setProfilePhotoLocation("");
        celeb3.setFamily("A32424B");
        celebList.add(celeb3);

        Beneficiary celeb4 = new Beneficiary();
        celeb4.setName("Andres Lopez");
        celeb4.setID("100033242");
        celeb4.setNation("Colombia");
        celeb4.setProfilePhotoLocation("");
        celeb4.setFamily("A32424B");
        celebList.add(celeb4);

        Beneficiary celeb5 = new Beneficiary();
        celeb5.setName("Oscar Moreno");
        celeb5.setID("1000324232");
        celeb5.setNation("Colombia");
        celeb5.setProfilePhotoLocation("");
        celeb5.setFamily("A32424B");
        celebList.add(celeb5);

        Beneficiary celeb6 = new Beneficiary();
        celeb6.setName("Pedro Martinez");
        celeb6.setID("102323121");
        celeb6.setNation("Colombia");
        celeb6.setProfilePhotoLocation("");
        celeb6.setFamily("A32424B");
        celebList.add(celeb6);

        Beneficiary celeb7 = new Beneficiary();
        celeb7.setName("Jose Cruz");
        celeb7.setID("10002423");
        celeb7.setNation("Colombia");
        celeb7.setProfilePhotoLocation("");
        celeb7.setFamily("A32424B");
        celebList.add(celeb7);

        Beneficiary celeb8 = new Beneficiary();
        celeb8.setName("Juan Martinez");
        celeb8.setID("100242323");
        celeb8.setNation("Colombia");
        celeb8.setProfilePhotoLocation("");
        celeb8.setFamily("A32424B");
        celebList.add(celeb8);

        Beneficiary celeb9 = new Beneficiary();
        celeb9.setName("Martin Diaz");
        celeb9.setID("1002323213");
        celeb9.setNation("Colombia");
        celeb9.setProfilePhotoLocation("");
        celeb9.setFamily("A32424B");
        celebList.add(celeb9);

        Beneficiary celeb10 = new Beneficiary();
        celeb10.setName("Pedro Gomez");
        celeb10.setID("1000335648");
        celeb10.setNation("Colombia");
        celeb10.setProfilePhotoLocation("");
        celeb10.setFamily("A32424B");
        celebList.add(celeb10);

        Beneficiary celeb11 = new Beneficiary();
        celeb11.setName("Hugo Sanchez");
        celeb11.setID("10003231231");
        celeb11.setNation("Colombia");
        celeb11.setProfilePhotoLocation("");
        celeb11.setFamily("A32424B");
        celebList.add(celeb11);

        Beneficiary celeb12 = new Beneficiary();
        celeb12.setName("Martin Diaz");
        celeb12.setID("10212412");
        celeb12.setNation("Colombia");
        celeb12.setProfilePhotoLocation("");
        celeb12.setFamily("A32424B");
        celebList.add(celeb12);

        Beneficiary celeb13 = new Beneficiary();
        celeb13.setName("Jose Cruz");
        celeb13.setID("1002323");
        celeb13.setNation("Colombia");
        celeb13.setProfilePhotoLocation("");
        celeb13.setFamily("A32424B");
        celebList.add(celeb13);

        Beneficiary celeb14 = new Beneficiary();
        celeb14.setName("Oscar Moreno");
        celeb14.setID("1002141411");
        celeb14.setNation("Colombia");
        celeb14.setProfilePhotoLocation("");
        celeb14.setFamily("A32424B");
        celebList.add(celeb14);

        Beneficiary celeb15 = new Beneficiary();
        celeb15.setName("Pedro Alvarez");
        celeb15.setID("1002321312");
        celeb15.setNation("Colombia");
        celeb15.setProfilePhotoLocation("");
        celeb15.setFamily("A32424B");
        celebList.add(celeb15);

        Beneficiary celeb16 = new Beneficiary();
        celeb16.setName("Milton Pinilla");
        celeb16.setID("102123112");
        celeb16.setNation("Colombia");
        celeb16.setProfilePhotoLocation("");
        celeb16.setFamily("A32424B");
        celebList.add(celeb16);

        Beneficiary celeb17 = new Beneficiary();
        celeb17.setName("Elver Cruz");
        celeb17.setID("10077645");
        celeb17.setNation("Colombia");
        celeb17.setProfilePhotoLocation("");
        celeb17.setFamily("A32424B");
        celebList.add(celeb17);

        Beneficiary celeb18 = new Beneficiary();
        celeb18.setName("Rafael Vasquez");
        celeb18.setID("1035244322");
        celeb18.setNation("Colombia");
        celeb18.setProfilePhotoLocation("");
        celeb18.setFamily("A32424B");
        celebList.add(celeb18);

        Beneficiary celeb19 = new Beneficiary();
        celeb19.setName("David Felipe Avila");
        celeb19.setID("1000335648");
        celeb19.setNation("Colombia");
        celeb19.setProfilePhotoLocation("");
        celeb19.setFamily("A32424B");
        celebList.add(celeb19);


    }
}