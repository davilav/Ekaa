package com.pma.ekaa.ui.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;
import java.util.List;

public class SelectOptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private ArrayList<Data> arrayData;
    private ArrayList<Data> arrayDataFiltered;
    private onAdapterListener adapterListener;

    public SelectOptionAdapter(ArrayList<Data> arrayData, onAdapterListener adapterListener) {
        this.arrayData = arrayData;
        this.adapterListener = adapterListener;
        arrayDataFiltered = this.arrayData;
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
        ((ItemViewHolder) holder).bindData(arrayDataFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    arrayDataFiltered = arrayData;
                } else {
                    ArrayList<Data> filteredList = new ArrayList<>();
                    for (Data data : arrayData) {
                        if (data.getName().toLowerCase().contains(charString.toLowerCase()) || data.getId().toString().contains(charSequence)) {
                            filteredList.add(data);
                        }
                    }
                    arrayDataFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayDataFiltered = (ArrayList<Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
