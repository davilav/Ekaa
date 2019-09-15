package com.pma.ekaa.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.models.Beneficiary;
import com.pma.ekaa.Views.BeneficiaryActivity;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Beneficiary> beneficiaries;
    private Context context;

    public TextView txtName, txtID, txtnumberID, txtNation;
    public CircleImageView profileImage;
    public ImageView editInfo;
    public CheckBox checkBox;
    public Button btn;
    public Dialog myDialog;
    public TextView txtclose;
    public TextView kitchenName;
    public int contador=0;

    public ItemAdapter(Context context, List<Beneficiary> beneficiaries) {
        this.context = context;
        this.beneficiaries = beneficiaries;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        myDialog = new Dialog(parent.getContext());
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return beneficiaries.size();
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindData(beneficiaries.get(position));
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull View view) {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtID = view.findViewById(R.id.txtID);
            profileImage = view.findViewById(R.id.profileImage);
            txtNation = view.findViewById(R.id.txtNation);
            txtnumberID = view.findViewById(R.id.txtnumberID);
            editInfo = view.findViewById(R.id.editInfoButton);
            checkBox = view.findViewById(R.id.Atencion);
            btn = view.findViewById(R.id.countButton);

        }

        public void bindData(final Beneficiary beneficiary) {
            txtName.setText(beneficiary.getFirst_name() + " " + beneficiary.getSurname());
            txtID.setText(beneficiary.getDocument());
            txtnumberID.setText(Integer.toString(beneficiary.getId()));

            editInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String object = new Gson().toJson(beneficiary);
                    Intent intent = new Intent(context, BeneficiaryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(BeneficiaryActivity.OBJECT_BENEFICIARIES, object);
                    context.startActivity(intent);

                }
            });



            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    contador++;
                    btn.setText(Integer.toString(contador));

                    myDialog.setContentView(R.layout.kitchen_popup);
                    txtclose = myDialog.findViewById(R.id.txtclose);
                    kitchenName = myDialog.findViewById(R.id.kitchen_name);
                    txtclose.setText("X");
                    kitchenName.setText(beneficiary.getFirst_name()+" "+beneficiary.getSurname());
                    txtclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                            checkBox.setChecked(false);
                        }
                    });
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });


        }


    }

}


