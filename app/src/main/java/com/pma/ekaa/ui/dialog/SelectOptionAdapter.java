package com.pma.ekaa.ui.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;

public class SelectOptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Data> arrayData;
    private onAdapterListener adapterListener;

    public SelectOptionAdapter(ArrayList<Data> arrayData, onAdapterListener adapterListener) {
        this.arrayData = arrayData;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_option, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindData(arrayData.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleItem = itemView.findViewById(R.id.titleOption);
        }

        public void bindData(final Data data) {
            titleItem.setText(data.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterListener.ItemSelect(data);
                }
            });
        }

    }

    interface onAdapterListener {
        void ItemSelect(Data data);
    }
}
