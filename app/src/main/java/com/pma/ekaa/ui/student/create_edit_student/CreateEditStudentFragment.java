package com.pma.ekaa.ui.student.create_edit_student;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.student.StudentActivity;
import com.pma.ekaa.utils.PreferencesHelper;
import com.pma.ekaa.utils.Utils;

import java.util.Calendar;


public class CreateEditStudentFragment extends Fragment implements View.OnClickListener {

    private int selectItem;
    private Result objectBeneficiary;

    private String getUID;
    private String id;

    private EditText namebeneficiary, seconenamebeneficiary, lastnamebeneficiary, surnamebeneficiary, documentbeneficiary, ethnicGroup;
    private EditText nationalitybeneficiary, documentTypebeneficiary, genderbeneficiary;
    private Button btnRecovery;
    private TextView birthdatebeneficiary, titleForm;
    private OnFragmentInteractionListener mListener;

    public CreateEditStudentFragment() {

    }

    public static CreateEditStudentFragment newInstance(int selectItem, String objectBeneficiary) {
        CreateEditStudentFragment fragment = new CreateEditStudentFragment();
        Bundle args = new Bundle();
        args.putInt(StudentActivity.SELECTED_ITEM, selectItem);
        args.putString(StudentActivity.OBJECT_BENEFICIARIES, objectBeneficiary);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectItem = getArguments().getInt(StudentActivity.SELECTED_ITEM);
            if(selectItem == BeneficiaryActivity.EDIT) {
                objectBeneficiary = new Gson().fromJson(getArguments().getString(BeneficiaryActivity.OBJECT_BENEFICIARIES), Result.class);
            } else {
                objectBeneficiary = new Result();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_edit_student, container, false);
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
        birthdatebeneficiary = view.findViewById(R.id.birthdatebeneficiary);
        nationalitybeneficiary = view.findViewById(R.id.nationalitybeneficiary);
        ethnicGroup = view.findViewById(R.id.ethnicGroup);
        btnRecovery = view.findViewById(R.id.btn_recovery);

        btnRecovery.setOnClickListener(this);
        genderbeneficiary.setOnClickListener(this);
        nationalitybeneficiary.setOnClickListener(this);
        documentTypebeneficiary.setOnClickListener(this);

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

        if(selectItem == StudentActivity.EDIT){
            titleForm.setText("Editar Estudiante");
            getUID = objectBeneficiary.getUid();
            id = objectBeneficiary.getId().toString();
            btnRecovery.setText("Actualizar");
            setInfoForm();
        } else {
            btnRecovery.setText("Registrar");
            titleForm.setText("Crear Estudiante");
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
        ethnicGroup.setText(objectBeneficiary.getEthnicity());
        birthdatebeneficiary.setText(objectBeneficiary.getBirth_date());

        if(objectBeneficiary.getNationality() != null) {
            nationalitybeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getNationality(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_NATIONALITY, "")));
        } else {
            nationalitybeneficiary.setText("");
        }

        if(objectBeneficiary.getDocument_type() != null) {
            documentTypebeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getDocument_type(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DOCUMENTS, "")));
        } else {
            documentTypebeneficiary.setText("");
        }

        if(objectBeneficiary.getGender() != null) {
            genderbeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getGender(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GENDERS, "")));
        } else {
            genderbeneficiary.setText("");
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
                break;
            case R.id.genderbeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GENDERS, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setGender(data.getId());
                                genderbeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(),"");
                break;
            case R.id.nationalitybeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_NATIONALITY, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setNationality(data.getId());
                                nationalitybeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.documentTypebeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DOCUMENTS, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setDocument_type(data.getId());
                                documentTypebeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            default:
                break;
        }
    }


    public interface OnFragmentInteractionListener {

    }
}
