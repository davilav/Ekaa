package com.pma.ekaa.ui.student.show_student;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.student.StudentActivity;
import com.pma.ekaa.utils.PreferencesHelper;
import com.pma.ekaa.utils.Utils;

public class ShowStudentFragment extends Fragment implements View.OnClickListener {

    private Result objectBeneficiary;

    private TextView name;
    private TextView familyCode;
    private TextView documentType;
    private TextView documentNumber;
    private TextView nation;
    private TextView gender;
    private TextView registration;
    private TextView group;
    private TextView birthdate;
    private TextView completeName;

    private OnFragmentInteractionListener mListener;

    public ShowStudentFragment() {
        // Required empty public constructor
    }


    public static ShowStudentFragment newInstance(int selectItem, String objectBeneficiary) {
        ShowStudentFragment fragment = new ShowStudentFragment();
        Bundle args = new Bundle();
        args.putString(StudentActivity.OBJECT_BENEFICIARIES, objectBeneficiary);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            objectBeneficiary = new Gson().fromJson(getArguments().getString(StudentActivity.OBJECT_BENEFICIARIES), Result.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_student, container, false);
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
        registration = view.findViewById(R.id.tv_date);
        group = view.findViewById(R.id.tv_group);
        completeName = view.findViewById(R.id.tv_complete_name);
        Button editBeneficiary = view.findViewById(R.id.editKitchenbutton);

        editBeneficiary.setOnClickListener(this);

        completeBeneficiary();

    }

    private void completeBeneficiary() {

        name.setText(objectBeneficiary.getFirstName()+" "+ objectBeneficiary.getSurname());
        birthdate.setText(objectBeneficiary.getBirthDate());
        completeName.setText(objectBeneficiary.getFirstName()+" "+ objectBeneficiary.getSecondName()+" "+ objectBeneficiary.getSurname()+" "+ objectBeneficiary.getSecondSurname());
        documentNumber.setText(objectBeneficiary.getDocument());
        registration.setText(objectBeneficiary.getRegistrationDate());
        nation.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getNationality(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_NATIONALITY, "")));
        gender.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getGender(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GENDERS, "")));
        group.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getSchoolGroup(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GROUPS, "")));
        documentType.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getDocumentType(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DOCUMENTS, "")));
        familyCode.setText(objectBeneficiary.getHouseholdCode());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        mListener.editStudent();
    }

    public interface OnFragmentInteractionListener {
        void editStudent();
    }
}
