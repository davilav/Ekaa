package com.pma.ekaa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.models.Beneficiary;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<Beneficiary> celebrityList;

    public ItemAdapter(List<Beneficiary> celebrityList) {
        this.celebrityList = celebrityList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Beneficiary item = celebrityList.get(position);
        holder.txtName.setText(item.getName());
        holder.txtID.setText(item.getID());
        holder.txtNation.setText(item.getNation());
        holder.txtFamily.setText(item.getFamily());
    }

    @Override
    public int getItemCount() {
        return celebrityList.size();
    }
}