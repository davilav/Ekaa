package com.pma.ekaa.ui.attendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.AttendanceDetail;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AttendanceDetail> data;

    public AttendanceAdapter(ArrayList<AttendanceDetail> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView detailCity, detailOffice, detailDate, detailTime, detailType, detailFood;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            detailCity = itemView.findViewById(R.id.detailCity);
            detailOffice = itemView.findViewById(R.id.detailOffice);
            detailDate = itemView.findViewById(R.id.detailDate);
            detailTime = itemView.findViewById(R.id.detailTime);
            detailType = itemView.findViewById(R.id.detailType);
            detailFood = itemView.findViewById(R.id.detailFood);


        }

        public void bindData(final AttendanceDetail data) {
            String date = data.getYear()+"/"+data.getMonth();
            detailDate.setText(date);
            detailOffice.setText(data.getInstitution());
            detailCity.setText(data.getGeolocation());
            detailTime.setText(data.getModalityType());
            detailType.setText(data.getPartner());
            detailFood.setText(data.getModality());
        }

    }

}
