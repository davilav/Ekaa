package com.pma.ekaa.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.Views.BeneficiaryActivity;
import com.pma.ekaa.Views.CreateBeneficiaryActivity;
import com.pma.ekaa.apis.ApiClient;
import com.pma.ekaa.models.Attendance;
import com.pma.ekaa.models.Result;
import com.pma.ekaa.models.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import github.ishaan.buttonprogressbar.ButtonProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> beneficiaries;
    private Context context;

    public TextView txtName, txtID, txtnumberID, txtNation;
    public CircleImageView profileImage;
    public ImageView editInfo;
    public CheckBox checkBox;
    public Button btn;
    public Dialog myDialog;
    public TextView txtclose;
    public TextView kitchenName;
    public TextView firstCom,secondCom,thirdCom;

    String token = Utils.getInstance().getObj().getToken();

    public int contador=0;

    public ItemAdapter(Context context, List<Result> beneficiaries) {
        this.context = context;
        this.beneficiaries = beneficiaries;
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
            editInfo = view.findViewById(R.id.editInfoButton);
            checkBox = view.findViewById(R.id.Atencion);
            btn = view.findViewById(R.id.countButton);

        }

        public void bindData(final Result result) {
            txtName.setText(result.getFirstName() + " " + result.getSurname());
            txtID.setText(result.getDocument());
            txtnumberID.setText(Integer.toString(result.getId()));

            editInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String object = new Gson().toJson(result);
                    Intent intent = new Intent(context, BeneficiaryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(BeneficiaryActivity.OBJECT_BENEFICIARIES, object);
                    context.startActivity(intent);

                }
            });



            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    contador++;
                    btn.setText(Integer.toString(contador));

                    myDialog.setContentView(R.layout.kitchen_popup);
                    txtclose = myDialog.findViewById(R.id.txtclose);
                    kitchenName = myDialog.findViewById(R.id.kitchen_name);
                    txtclose.setText("X");
                    kitchenName.setText(result.getFirstName()+" "+ result.getSurname());
                    txtclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                            checkBox.setChecked(false);
                        }
                    });

                    final ButtonProgressBar bar = myDialog.findViewById(R.id.btnfollow);
                    bar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bar.startLoader();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    registerAttendance();
                                    bar.stopLoader();
                                }
                            }, 4000);

                        }
                    });

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });


        }


    }

    private void registerAttendance() {

                Attendance attendance = new Attendance("1","1","Comedor","","","");
                Call<Attendance> call = ApiClient.getInstance().getApi().registerAttendance(attendance,("Token "+token));
                call.enqueue(new Callback<Attendance>() {
                    @Override
                    public void onResponse(Call<Attendance> call, Response<Attendance> response) {
                        Toasty.success(context, "Atención registrada exitosamente", Toast.LENGTH_SHORT, true).show();


                    }

            @Override
            public void onFailure(Call<Attendance> call, Throwable t) {
                Toasty.error(context, "Error al registrar la atención", Toast.LENGTH_SHORT, true).show();
            }
        });


    }

}

