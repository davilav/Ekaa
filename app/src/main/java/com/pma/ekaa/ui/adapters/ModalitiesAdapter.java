package com.pma.ekaa.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.AttendanceToday;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModalitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Modality> modality;
    private OnListenerAdapter mListener;

    private TextView modalityname;
    private LinearLayout modalitybox;
    private CheckBox modalitycheckbox;
    private ArrayList<AttendanceToday> attendanceToday;

    public ModalitiesAdapter(Context context, List<Modality> modality, OnListenerAdapter mListener, ArrayList<AttendanceToday> attendanceToday) {
        this.modality = modality;
        this.mListener = mListener;
        this.attendanceToday = attendanceToday;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modality_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindData(modality.get(position));
    }

    @Override
    public int getItemCount() {
        return modality.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemViewHolder(@NonNull View view) {
            super(view);

            modalityname = view.findViewById(R.id.first_complement);
            modalitybox = view.findViewById(R.id.color_first);
            modalitycheckbox = view.findViewById(R.id.modalitycheckbox);

        }

        void bindData(final Modality modality) {
            modalityname.setText(modality.getName());
            modalitybox.setBackgroundColor(Color.parseColor(modality.getColor()));

            for (int cont = 0; cont < attendanceToday.size(); cont++) {
                if (attendanceToday.get(cont).getModalityId() == modality.getId()) {
                    modalitycheckbox.setEnabled(false);
                    modalitycheckbox.setChecked(true);
                    break;
                }
            }

            modalitycheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        mListener.registerAttendace(modality.getId());
                    }
                }
            });

        }


    }

    public interface OnListenerAdapter {
        void registerAttendace(int modalityID);
    }

}

