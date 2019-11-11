package com.pma.ekaa.ui.beneficiary.create_edit_beneficiary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;
import com.pma.ekaa.utils.PreferencesHelper;

import java.util.Calendar;


public class CreateEditBeneficiaryFragment extends Fragment implements View.OnClickListener {

    private int selectItem;
    private int optionAction;
    private Result objectBeneficiary;
    private String agreement;

    private String getUID;
    private String id;

    private EditText namebeneficiary, seconenamebeneficiary, lastnamebeneficiary, surnamebeneficiary, documentbeneficiary, pregnantbeneficiary, phonebeneficiary,
            familybeneficiary, migratoryStatus, maritalStatus, ethnicGroup, note, infoAditional;
    private EditText nationalitybeneficiary, documentTypebeneficiary, genderbeneficiary;
    private TextView birthdatebeneficiary, titleForm;
    private Button btnRecovery;

    private OnFragmentInteractionListener mListener;

    public CreateEditBeneficiaryFragment() {
        // Required empty public constructor
    }

    public static CreateEditBeneficiaryFragment newInstance(int selectItem, int optionAction, String agreement, String objectBeneficiary) {
        CreateEditBeneficiaryFragment fragment = new CreateEditBeneficiaryFragment();
        Bundle args = new Bundle();
        args.putInt(NotSchoolActivity.OPTION_ACTION, optionAction);
        args.putInt(BeneficiaryActivity.SELECTED_ITEM, selectItem);
        args.putString(BeneficiaryActivity.OBJECT_BENEFICIARIES, objectBeneficiary);
        args.putString(BeneficiaryActivity.OPTION_AGREEMENT, agreement);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            optionAction = getArguments().getInt(NotSchoolActivity.OPTION_ACTION);
            selectItem = getArguments().getInt(BeneficiaryActivity.SELECTED_ITEM);
            objectBeneficiary = new Gson().fromJson(getArguments().getString(BeneficiaryActivity.OBJECT_BENEFICIARIES), Result.class);
            agreement = getArguments().getString(BeneficiaryActivity.OPTION_AGREEMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int currentLayout;
        if(optionAction == NotSchoolActivity.KITCHEN){
            currentLayout = R.layout.layout_kitchen_beneficiary;
        } else if(optionAction == NotSchoolActivity.WALKERS){
            currentLayout = R.layout.layout_walkers_beneficiary;
        } else {
            currentLayout = R.layout.layout_inkind_beneficiary;
        }
        return inflater.inflate(currentLayout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleForm = view.findViewById(R.id.titleForm);

        namebeneficiary = view.findViewById(R.id.namebeneficiary);
        seconenamebeneficiary = view.findViewById(R.id.seconenamebeneficiary);
        lastnamebeneficiary = view.findViewById(R.id.lastnamebeneficiary);
        surnamebeneficiary = view.findViewById(R.id.surnamebeneficiary);
        documentbeneficiary = view.findViewById(R.id.documentbeneficiary);
        documentTypebeneficiary = view.findViewById(R.id.documentTypebeneficiary);
        genderbeneficiary = view.findViewById(R.id.genderbeneficiary);
        phonebeneficiary = view.findViewById(R.id.phonebeneficiary);
        birthdatebeneficiary = view.findViewById(R.id.birthdatebeneficiary);
        nationalitybeneficiary = view.findViewById(R.id.nationalitybeneficiary);
        pregnantbeneficiary = view.findViewById(R.id.pregnantbeneficiary);
        maritalStatus = view.findViewById(R.id.maritalStatus);
        familybeneficiary = view.findViewById(R.id.familybeneficiary);
        btnRecovery = view.findViewById(R.id.btn_recovery);

        if(optionAction == NotSchoolActivity.KITCHEN){
            note = view.findViewById(R.id.note);
            infoAditional = view.findViewById(R.id.infoAditional);
        } else if(optionAction == NotSchoolActivity.WALKERS){
            migratoryStatus = view.findViewById(R.id.migratoryStatus);
            ethnicGroup = view.findViewById(R.id.ethnicGroup);
        } else {

        }

        btnRecovery.setOnClickListener(this);
        genderbeneficiary.setOnClickListener(this);
        nationalitybeneficiary.setOnClickListener(this);
        documentTypebeneficiary.setOnClickListener(this);
        maritalStatus.setOnClickListener(this);

        birthdatebeneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                //se crea la ventana del calendario desde donde se elegira la fecha
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.show();
            }
        });

        if(selectItem == BeneficiaryActivity.EDIT){
            titleForm.setText("Editar Beneficiario");
            agreement = objectBeneficiary.getAgreement();
            getUID = objectBeneficiary.getUid();
            id = objectBeneficiary.getId().toString();
            btnRecovery.setText("Actualizar");
            setInfoForm();
        } else {
            btnRecovery.setText("Registrar");
            titleForm.setText("Crear Beneficiario");
            getUID = "";
            id = "";
        }

    }

    private void setInfoForm() {

        namebeneficiary.setText(objectBeneficiary.getFirst_name());
        seconenamebeneficiary.setText(objectBeneficiary.getSecond_name());
        lastnamebeneficiary.setText(objectBeneficiary.getSurname());
        surnamebeneficiary.setText(objectBeneficiary.getSecond_surname());
        documentbeneficiary.setText(objectBeneficiary.getDocument());
        //SPINNER
        //documentTypebeneficiary, genderbeneficiary, nationalitybeneficiary
        phonebeneficiary.setText(objectBeneficiary.getPhone());
        birthdatebeneficiary.setText(objectBeneficiary.getBirth_date());
        pregnantbeneficiary.setText(objectBeneficiary.getPregnant());
        maritalStatus.setText(objectBeneficiary.getMarital_status());
        familybeneficiary.setText(objectBeneficiary.getHousehold_code());

        if(optionAction == NotSchoolActivity.KITCHEN){
            note.setText(objectBeneficiary.getNote());
            infoAditional.setText(objectBeneficiary.getAditional_information());
        } else if(optionAction == NotSchoolActivity.WALKERS){
            migratoryStatus.setText(objectBeneficiary.getMigratory_status());
            ethnicGroup.setText(objectBeneficiary.getEthnicity());
        } else if(optionAction == NotSchoolActivity.INKIND){

        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        //al pasar la fecha y dar ok se setea la fecha en el textview
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            birthdatebeneficiary.setText(year + "/" + month + "/" + day);
        }
    };


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
        switch (view.getId()) {
            case R.id.btn_recovery:
                if (optionAction == NotSchoolActivity.KITCHEN){
                    RegisterBeneficiary registerBeneficiary = new RegisterBeneficiary(
                            getUID, 1, 1, 1, 1, 1, 1, 1, 1,
                            1, namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                            surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), null, birthdatebeneficiary.getText().toString(),
                            pregnantbeneficiary.getText().toString(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                            familybeneficiary.getText().toString(),agreement);
                    mListener.setUploadBeneficiary(id ,registerBeneficiary, selectItem);
                } else  if (optionAction == NotSchoolActivity.WALKERS) {

                } else if(optionAction == NotSchoolActivity.INKIND) {

                }
                break;
            case R.id.genderbeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GENDERS, ""),
                        false,
                        new SelectOptionDialog.onListenerInterface() {
                    @Override
                    public void optionSelect(Data data) {
                        genderbeneficiary.setText(data.getName());
                    }
                }).show(getActivity().getSupportFragmentManager(),"");
                break;
            case R.id.nationalitybeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_NATIONALITY, ""),
                        false,
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                nationalitybeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.documentTypebeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DOCUMENTS, ""),
                        false,
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                documentTypebeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
            case R.id.maritalStatus:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_MARITAL, ""),
                        false,
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                maritalStatus.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
            default:
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void setUploadBeneficiary(String id, RegisterBeneficiary registerBeneficiary, int optionAction);
    }
}
