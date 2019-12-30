
package com.pma.ekaa.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> beneficiaries;

    private onListenerAdapter mListener;

    private TextView txtName, txtID, txtnumberID, txtNation, firstComplement, secondComplement, thirdComplement;
    private CircleImageView profileImage;
    private ImageView editInfo, attention;
    private TextView detail;
    private TextView cont;
    private Dialog myDialog;
    private TextView txtclose;
    private TextView kitchenName;

    public int contador=0;

    public ItemAdapter(Context context, List<Result> beneficiaries, ArrayList<Modality> modalities, int institutionID, onListenerAdapter mListener) {
        this.beneficiaries = beneficiaries;
        this.mListener = mListener;
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

        ItemViewHolder(@NonNull View view) {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtID = view.findViewById(R.id.txtID);
            profileImage = view.findViewById(R.id.profileImage);
            txtNation = view.findViewById(R.id.txtNation);
            txtnumberID = view.findViewById(R.id.txtnumberID);
            attention = view.findViewById(R.id.image_atention);
            //editInfo = view.findViewById(R.id.editInfoButton);
            cont = view.findViewById(R.id.countButton);
            CheckBox AM = view.findViewById(R.id.AM);
            CheckBox PM = view.findViewById(R.id.PM);
            CheckBox lunch = view.findViewById(R.id.lunch);
        }

        void bindData(final Result result) {

            txtName.setText(result.getFirst_name() + " " + result.getSurname());
            txtID.setText(result.getDocument());
            txtnumberID.setText(result.getHousehold_code());
            Integer id = result.getId();
            if(Objects.equals(result.getAttendance(), null)){
                cont.setText("0");
            } else {
                cont.setText(result.getAttendance());
            }
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
