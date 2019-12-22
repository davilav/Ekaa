
package com.pma.ekaa.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.school.SchoolActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> beneficiaries;
    private Context context;

    private onListenerAdapter mListener;

    private TextView txtName, txtID, txtnumberID, txtNation, firstComplement, secondComplement, thirdComplement;
    private CircleImageView profileImage;
    private ImageView editInfo, attention;
    private TextView detail;
    private CheckBox AM,lunch,PM;
    private Button cont;
    private Dialog myDialog;
    private TextView txtclose;
    private TextView kitchenName;
    private ArrayList<Modality> arrayModality;
    private int institutionID;

    Integer id = 0;
    public int contador=0;

    public ItemAdapter(Context context, List<Result> beneficiaries, ArrayList<Modality> modalities, int institutionID, onListenerAdapter mListener) {
        this.context = context;
        this.beneficiaries = beneficiaries;
        this.mListener = mListener;
        this.arrayModality = modalities;
        this.institutionID = institutionID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
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
            attention = view.findViewById(R.id.image_atention);
            //editInfo = view.findViewById(R.id.editInfoButton);
            cont = view.findViewById(R.id.countButton);
            AM = view.findViewById(R.id.AM);
            PM = view.findViewById(R.id.PM);
            lunch = view.findViewById(R.id.lunch);
        }

        public void bindData(final Result result) {

            txtName.setText(result.getFirst_name() + " " + result.getSurname());
            txtID.setText(result.getDocument());
            txtnumberID.setText(result.getDocument());
            id = result.getId();
            cont.setText(result.getAttendance());
            txtNation.setText(result.getBirth_date());

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.showBeneficiary(result);
                }
            });

            attention.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.attendanceToday(result);
                }
            });


        }

    }

    public interface onListenerAdapter {
        void showBeneficiary(Result beneficiary);
        void attendanceToday(Result beneficiary);
    }

}
