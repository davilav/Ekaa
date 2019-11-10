package com.pma.ekaa.ui.beneficiary.show_beneficiary;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;

public class ShowBeneficiaryFragment extends Fragment implements View.OnClickListener {

    private int selectItem;
    private int optionAction;
    private Result objectBeneficiary;

    private ImageView back;
    private TextView name,familyCode,documentType,documentNumber, nation,gender,phone,registration,info,birthdate,completeName,modality;
    private Button editBeneficiary;

    private OnFragmentInteractionListener mListener;

    public ShowBeneficiaryFragment() {
        // Required empty public constructor
    }


    public static ShowBeneficiaryFragment newInstance(int selectItem, int optionAction, String objectBeneficiary) {
        ShowBeneficiaryFragment fragment = new ShowBeneficiaryFragment();
        Bundle args = new Bundle();
        args.putInt(NotSchoolActivity.OPTION_ACTION, selectItem);
        args.putInt(BeneficiaryActivity.SELECTED_ITEM, optionAction);
        args.putString(BeneficiaryActivity.OBJECT_BENEFICIARIES, objectBeneficiary);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectItem = getArguments().getInt(NotSchoolActivity.OPTION_ACTION);
            optionAction = getArguments().getInt(BeneficiaryActivity.SELECTED_ITEM);
            objectBeneficiary = new Gson().fromJson(getArguments().getString(BeneficiaryActivity.OBJECT_BENEFICIARIES), Result.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_beneficiary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        name = view.findViewById(R.id.tv_name);
        familyCode = view.findViewById(R.id.tv_family);
        documentType = view.findViewById(R.id.tv_document);
        documentNumber = view.findViewById(R.id.tv_number_document);
        birthdate = view.findViewById(R.id.tv_birthdate);
        nation = view.findViewById(R.id.tv_nation);
        gender = view.findViewById(R.id.tv_gender);
        phone = view.findViewById(R.id.tv_phone);
        registration = view.findViewById(R.id.tv_date);
        info = view.findViewById(R.id.tv_info);
        completeName = view.findViewById(R.id.tv_complete_name);
        modality = view.findViewById(R.id.tv_modality);
        editBeneficiary = view.findViewById(R.id.editKitchenbutton);

        editBeneficiary.setOnClickListener(this);

        completeBeneficiary();

    }

    private void completeBeneficiary() {
        name.setText(objectBeneficiary.getFirst_name()+" "+ objectBeneficiary.getSurname());
        birthdate.setText(objectBeneficiary.getBirth_date());
        completeName.setText(objectBeneficiary.getFirst_name()+" "+ objectBeneficiary.getSecond_name()+" "+ objectBeneficiary.getSurname()+" "+ objectBeneficiary.getSecond_surname());
        documentNumber.setText(objectBeneficiary.getDocument());
        phone.setText(objectBeneficiary.getPhone());
        registration.setText(objectBeneficiary.getRegistration_date());
        info.setText(objectBeneficiary.getAditional_information());
        modality.setText("");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        mListener.editBeneficiary();
    }

    public interface OnFragmentInteractionListener {
        void editBeneficiary();
    }
}
