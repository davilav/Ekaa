
package com.pma.ekaa.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private TextView txtName;
    private TextView txtID;
    private TextView txtnumberID;
    private TextView txtNation;
    private CircleImageView profileImage;
    private ImageView  attention;
    private TextView cont;

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
            cont = view.findViewById(R.id.countButton);
        }

        void bindData(final Result result) {

            txtName.setText(result.getFirstName() + " " + result.getSurname());
            txtID.setText(result.getDocument());
            txtnumberID.setText(result.getHouseholdCode());
            if(Objects.equals(result.getAttendance(), null)){
                cont.setText("0");
            } else {
                cont.setText(result.getAttendance());
            }
            txtNation.setText(result.getBirthDate());

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
