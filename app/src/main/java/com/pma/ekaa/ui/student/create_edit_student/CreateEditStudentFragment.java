package com.pma.ekaa.ui.student.create_edit_student;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Beneficiary;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.RegisterStudent;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.student.StudentActivity;
import com.pma.ekaa.utils.PreferencesHelper;
import com.pma.ekaa.utils.Utils;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;


public class CreateEditStudentFragment extends Fragment implements View.OnClickListener {

    private static String REGISTER_STUDENT = "register_student";

    private int selectItem;
    private Result objectBeneficiary;

    private RegisterStudent registerStudent;

    private String getUID;
    private Integer id;
    private String stateBelongs = "1";

    private EditText namebeneficiary, seconenamebeneficiary, lastnamebeneficiary, surnamebeneficiary, documentbeneficiary, ethnicGroup;
    private EditText nationalitybeneficiary, documentTypebeneficiary, genderbeneficiary, disabilitiesbenenficiary,programbeneficiary,groupbeneficiary,classbeneficiary;
    private Button btnRecovery;
    private TextView birthdatebeneficiary, titleForm;
    private CheckBox belongsProgram;
    private OnFragmentInteractionListener mListener;

    public CreateEditStudentFragment() {

    }

    public static CreateEditStudentFragment newInstance(int selectItem, String objectBeneficiary, String registerStudent) {
        CreateEditStudentFragment fragment = new CreateEditStudentFragment();
        Bundle args = new Bundle();
        args.putInt(StudentActivity.SELECTED_ITEM, selectItem);
        args.putString(StudentActivity.OBJECT_BENEFICIARIES, objectBeneficiary);
        args.putString(REGISTER_STUDENT, registerStudent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectItem = getArguments().getInt(StudentActivity.SELECTED_ITEM);
            registerStudent = new Gson().fromJson(getArguments().getString(REGISTER_STUDENT), RegisterStudent.class);
            if (selectItem == BeneficiaryActivity.EDIT) {
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
        disabilitiesbenenficiary = view.findViewById(R.id.disabilitiesbeneficiary);
        programbeneficiary = view.findViewById(R.id.programbeneficiary);
        groupbeneficiary = view.findViewById(R.id.groupbeneficiary);
        classbeneficiary = view.findViewById(R.id.classbeneficiary);
        ethnicGroup = view.findViewById(R.id.ethnicGroup);
        btnRecovery = view.findViewById(R.id.btn_recovery);
        belongsProgram = view.findViewById(R.id.chk_belongs_program);

        btnRecovery.setOnClickListener(this);
        genderbeneficiary.setOnClickListener(this);
        nationalitybeneficiary.setOnClickListener(this);
        documentTypebeneficiary.setOnClickListener(this);
        disabilitiesbenenficiary.setOnClickListener(this);
        programbeneficiary.setOnClickListener(this);
        groupbeneficiary.setOnClickListener(this);
        classbeneficiary.setOnClickListener(this);

        belongsProgram.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if(state){
                    stateBelongs = "1";
                } else {
                    stateBelongs = "0";
                }
            }
        });

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

        if (selectItem == StudentActivity.EDIT) {
            titleForm.setText(getResources().getString(R.string.editStudent));
            getUID = objectBeneficiary.getUid();
            id = objectBeneficiary.getId();
            btnRecovery.setText(getResources().getString(R.string.update));
            setInfoForm();
        } else {
            btnRecovery.setText(getResources().getString(R.string.registerbeneficiary));
            titleForm.setText(getResources().getString(R.string.createstudent));
            getUID = "";
            id = null;
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
        classbeneficiary.setText(String.valueOf(objectBeneficiary.getSchoolClass()));

        if(objectBeneficiary.getBelongsProgram().equals(1)){
            belongsProgram.setChecked(true);
            stateBelongs = "1";
        } else if(objectBeneficiary.getBelongsProgram().equals(0)){
            belongsProgram.setChecked(false);
            stateBelongs = "0";
        }

        if (objectBeneficiary.getNationality() != null) {
            nationalitybeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getNationality(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_NATIONALITY, "")));
        } else {
            nationalitybeneficiary.setText("");
        }

        if (objectBeneficiary.getDocument_type() != null) {
            documentTypebeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getDocument_type(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DOCUMENTS, "")));
        } else {
            documentTypebeneficiary.setText("");
        }

        if (objectBeneficiary.getGender() != null) {
            genderbeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getGender(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GENDERS, "")));
        } else {
            genderbeneficiary.setText("");
        }

        if (objectBeneficiary.getDisability() != null) {
            disabilitiesbenenficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getDisability(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DISABILITIES, "")));
        } else {
            disabilitiesbenenficiary.setText("");
        }

        if (objectBeneficiary.getSchoolProgram() != null) {
            programbeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getSchoolProgram(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_PROGRAMS, "")));
        } else {
            programbeneficiary.setText("");
        }

        if (objectBeneficiary.getSchoolGroup() != null) {
            groupbeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getSchoolGroup(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GROUPS, "")));
        } else {
            groupbeneficiary.setText("");
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
                if(TextUtils.isEmpty(disabilitiesbenenficiary.getText())){
                    disabilitiesbenenficiary.setError("El campo discapacidad es obligatorio");
                    validate();
                }else {
                    Beneficiary beneficiary = new Beneficiary(
                            id, namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                            surnamebeneficiary.getText().toString(), objectBeneficiary.getDocument_type(), documentbeneficiary.getText().toString(), objectBeneficiary.getGender(),
                            ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(), objectBeneficiary.getNationality(), documentbeneficiary.getText().toString(), objectBeneficiary.getDisability());
                    registerStudent.setBeneficiary(beneficiary);
                    registerStudent.setSchoolProgram(objectBeneficiary.getSchoolProgram());
                    registerStudent.setSchoolGroup(objectBeneficiary.getSchoolGroup());
                    registerStudent.setSchoolClass(Integer.parseInt(classbeneficiary.getText().toString()));
                    registerStudent.setBelongsProgram(stateBelongs);
                    mListener.setUploadBeneficiary(registerStudent, selectItem);
                }
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
                        }).show(getActivity().getSupportFragmentManager(), "");
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

            case R.id.disabilitiesbeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DISABILITIES, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setDisability(data.getId());
                                disabilitiesbenenficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.programbeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_PROGRAMS, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setSchoolProgram(data.getId());
                                programbeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;

            case R.id.groupbeneficiary:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GROUPS, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setSchoolGroup(data.getId());
                                groupbeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            default:
                break;
        }
    }

    private void validate() {

        if(TextUtils.isEmpty(namebeneficiary.getText())){
            namebeneficiary.setError("El primer nombre es obligatorio");
        }
        if(TextUtils.isEmpty(lastnamebeneficiary.getText())){
            lastnamebeneficiary.setError("El apellido es obligatorio");
        }
        if(TextUtils.isEmpty(documentbeneficiary.getText())){
            documentbeneficiary.setError("El documento es obligatorio");
        }
        if(TextUtils.isEmpty(documentTypebeneficiary.getText())){
            documentTypebeneficiary.setError("El tipo de documento es obligatorio");
        }
        if(TextUtils.isEmpty(genderbeneficiary.getText())) {
            genderbeneficiary.setError("El genero es obligatorio");
        }
        if(TextUtils.isEmpty(nationalitybeneficiary.getText())){
            nationalitybeneficiary.setError("El primer nombre es obligatorio");
        }
        if(TextUtils.isEmpty(classbeneficiary.getText())){
            classbeneficiary.setError("El primer nombre es obligatorio");
        }
        if(TextUtils.isEmpty(groupbeneficiary.getText())){
            groupbeneficiary.setError("El primer nombre es obligatorio");
        }
        if(TextUtils.isEmpty(programbeneficiary.getText())){
            programbeneficiary.setError("El primer nombre es obligatorio");
        }
        Toasty.warning(getContext(),"Por favor revise que los campos hayan sido llenados correctamente",Toasty.LENGTH_SHORT,true).show();

    }


    public interface OnFragmentInteractionListener {
        void setUploadBeneficiary(RegisterStudent registerStudent, int optionAction);
    }
}
