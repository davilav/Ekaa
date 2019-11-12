
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import github.ishaan.buttonprogressbar.ButtonProgressBar;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> beneficiaries;
    private Context context;

    private onListenerAdapter mListener;

    private TextView txtName, txtID, txtnumberID, txtNation, firstComplement, secondComplement, thirdComplement;
    private CircleImageView profileImage;
    private ImageView editInfo, attention;
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
            txtnumberID.setText(Integer.toString(result.getId()));
            id = result.getId();
            cont.setText(result.getAttendance());
            //cont.setBackgroundColor(Color.parseColor("#"+result.getColor()));

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.showBeneficiary(result);
                }
            });

            /*editInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*String object = new Gson().toJson(result);
                    Intent intent = new Intent(context, BeneficiaryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(BeneficiaryActivity.OBJECT_BENEFICIARIES, object);
                    context.startActivity(intent);

                    Intent intent = new Intent(context, BeneficiaryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(BeneficiaryActivity.SELECTED_ITEM, BeneficiaryActivity.EDIT);
                    intent.putExtra(NotSchoolActivity.SELECTED_ITEM, NotSchoolActivity.KITCHEN);
                    context.startActivity(intent);

                }
            });*/

            attention.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    myDialog.setContentView(R.layout.beneficiary_popup);

                    AM = myDialog.findViewById(R.id.AM);
                    PM = myDialog.findViewById(R.id.PM);
                    lunch = myDialog.findViewById(R.id.lunch);
                    txtclose = myDialog.findViewById(R.id.txtclose);
                    kitchenName = myDialog.findViewById(R.id.kitchen_name);
                    firstComplement = myDialog.findViewById(R.id.first_complement);
                    secondComplement = myDialog.findViewById(R.id.second_complement);
                    thirdComplement = myDialog.findViewById(R.id.third_complement);


                    //Se deberia implementar un recycler view para listar las opciones

                    firstComplement.setText(arrayModality.get(0).getName());
                    secondComplement.setText(arrayModality.get(1).getName());
                    thirdComplement.setText(arrayModality.get(2).getName());

                    ((LinearLayout) myDialog.findViewById(R.id.color_first)).setBackgroundColor(Color.parseColor(arrayModality.get(0).getColor()));
                    ((LinearLayout) myDialog.findViewById(R.id.color_second)).setBackgroundColor(Color.parseColor(arrayModality.get(1).getColor()));
                    ((LinearLayout) myDialog.findViewById(R.id.color_third)).setBackgroundColor(Color.parseColor(arrayModality.get(2).getColor()));

                    txtclose.setText("X");
                    kitchenName.setText(result.getFirst_name()+" "+ result.getSurname());

                    AM.setChecked(true);

                    AM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            if(isChecked){
                                PM.setChecked(false);
                                lunch.setChecked(false);
                            }
                        }
                    });

                    PM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            if(isChecked){
                                AM.setChecked(false);
                                lunch.setChecked(false);
                            }
                        }
                    });

                    lunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            if(isChecked){
                                PM.setChecked(false);
                                AM.setChecked(false);
                            }
                        }
                    });

                    txtclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });


                    final ButtonProgressBar bar = myDialog.findViewById(R.id.btnfollow);
                    bar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bar.startLoader();

                            int modality = 0;
                            if(AM.isChecked()){
                                modality = 1;
                            }else if (lunch.isChecked()){
                                modality = 2;
                            }else if(PM.isChecked()){
                                modality = 3;
                            }

                            mListener.registerAttendance(myDialog, institutionID, result.getId(), 1, modality);
                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });


        }

    }

    public interface onListenerAdapter {
        void registerAttendance(Dialog myDialog, int institution, int userID, int person, int modality);
        void showBeneficiary(Result beneficiary);
    }

}
