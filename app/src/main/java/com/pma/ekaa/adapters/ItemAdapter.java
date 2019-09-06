package com.pma.ekaa.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.models.Beneficiary;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<Beneficiary> beneficiaries;
    private Context context;

    public ItemAdapter(Context context, List<Beneficiary> beneficiaries) {
        this.context = context;
        this.beneficiaries = beneficiaries;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Beneficiary item = beneficiaries.get(position);
        holder.txtName.setText(beneficiaries.get(position).getFirst_name());
        holder.txtlastName.setText(beneficiaries.get(position).getSurname());
        holder.txtID.setText(beneficiaries.get(position).getDocument());
       // holder.txtNation.setText(item.getNationality());
        // holder.txtFamily.setText(beneficiaries.get(position).getHousehold_code());
    }

    @Override
    public int getItemCount() {
        return beneficiaries.size();
    }
}