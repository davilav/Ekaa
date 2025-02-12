package com.pma.ekaa.ui.beneficiary.create_edit_beneficiary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Beneficiary;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.dialog.SelectOptionDialog;
import com.pma.ekaa.ui.not_school.NotSchoolActivity;
import com.pma.ekaa.ui.student.create_edit_student.CreateEditStudentFragment;
import com.pma.ekaa.ui.welcome.WelcomeActivity;
import com.pma.ekaa.utils.PreferencesHelper;
import com.pma.ekaa.utils.Utils;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class CreateEditBeneficiaryFragment extends Fragment implements View.OnClickListener {

    private static final String FAMILY_CODE = "family_code";

    private int selectItem;
    private int optionAction;
    private Result objectBeneficiary;
    private String agreement;
    private String pobjectBeneficiary;
    private String familyCode = null;
    private String optionAgreement = "";
    private BottomSheetDialog deleteDialog;

    private String getUID;
    private String id;
    private Integer userID = Utils.getInstance().getDataUser().getUserId();

    private EditText namebeneficiary;
    private EditText seconenamebeneficiary;
    private EditText lastnamebeneficiary;
    private EditText surnamebeneficiary;
    private EditText documentbeneficiary;
    private EditText phonebeneficiary;
    private EditText migratoryStatus;
    private EditText maritalStatus;
    private EditText ethnicGroup;
    private EditText note;
    private EditText infoAditional;
    private EditText nationalitybeneficiary;
    private EditText documentTypebeneficiary;
    private EditText genderbeneficiary;
    private EditText recipientBeneficiary;
    private EditText householdRoleBeneficiary;
    private EditText disabilitiesbeneficiary;

    private TextView deletenamebeneficiary;

    private TextInputLayout tilnamebeneficiary;
    private TextInputLayout tillastnamebeneficiary;
    private TextInputLayout tildocumentbeneficiary;
    private TextInputLayout tilnationalitybeneficiary;
    private TextInputLayout tildocumentTypebeneficiary;
    private TextInputLayout tilgenderbeneficiary;

    private TextView birthdatebeneficiary;
    private CheckBox pregnantbeneficiary;
    private Button add, delete;
    CheckBox headFamily;

    private Boolean isHeadFamily = false;

    private OnFragmentInteractionListener mListener;

    public CreateEditBeneficiaryFragment() {
        // Required empty public constructor
    }

    public static CreateEditBeneficiaryFragment newInstance(int selectItem, int optionAction, String agreement, String objectBeneficiary, String familyCode) {
        CreateEditBeneficiaryFragment fragment = new CreateEditBeneficiaryFragment();
        Bundle args = new Bundle();
        args.putInt(NotSchoolActivity.OPTION_ACTION, optionAction);
        args.putInt(BeneficiaryActivity.SELECTED_ITEM, selectItem);
        args.putString(BeneficiaryActivity.OBJECT_BENEFICIARIES, objectBeneficiary);
        args.putString(BeneficiaryActivity.OPTION_AGREEMENT, agreement);
        args.putString(FAMILY_CODE, familyCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            optionAction = getArguments().getInt(NotSchoolActivity.OPTION_ACTION);
            selectItem = getArguments().getInt(BeneficiaryActivity.SELECTED_ITEM);
            agreement = getArguments().getString(BeneficiaryActivity.OPTION_AGREEMENT);
            familyCode = getArguments().getString(FAMILY_CODE);
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
        int currentLayout;
        if (optionAction == NotSchoolActivity.KITCHEN) {
            currentLayout = R.layout.layout_kitchen_beneficiary;
        } else if (optionAction == NotSchoolActivity.WALKERS) {
            currentLayout = R.layout.layout_walkers_beneficiary;
        } else {
            currentLayout = R.layout.layout_inkind_beneficiary;
        }
        return inflater.inflate(currentLayout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleForm = view.findViewById(R.id.titleForm);

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
        recipientBeneficiary = view.findViewById(R.id.recipientID);
        householdRoleBeneficiary = view.findViewById(R.id.houseHoldRole);
        disabilitiesbeneficiary = view.findViewById(R.id.disabilities);
        migratoryStatus = view.findViewById(R.id.migratoryStatus);

        tilnamebeneficiary = view.findViewById(R.id.til_namebeneficiary);
        tillastnamebeneficiary = view.findViewById(R.id.til_lastnamebeneficiary);
        tildocumentbeneficiary = view.findViewById(R.id.til_documentbeneficiary);
        tildocumentTypebeneficiary = view.findViewById(R.id.til_documentTypebeneficiary);
        tilgenderbeneficiary = view.findViewById(R.id.til_genderbeneficiary);
        tilnationalitybeneficiary = view.findViewById(R.id.til_nationalitybeneficiary);
        Button btnRecovery = view.findViewById(R.id.btn_recovery);
        headFamily = view.findViewById(R.id.chk_head_family);
        add = view.findViewById(R.id.btn_add);
        delete = view.findViewById(R.id.btn_delete);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        btnRecovery.setOnClickListener(this);
        genderbeneficiary.setOnClickListener(this);
        nationalitybeneficiary.setOnClickListener(this);
        documentTypebeneficiary.setOnClickListener(this);
        maritalStatus.setOnClickListener(this);
        disabilitiesbeneficiary.setOnClickListener(this);
        householdRoleBeneficiary.setOnClickListener(this);
        recipientBeneficiary.setOnClickListener(this);
        migratoryStatus.setOnClickListener(this);

        if (optionAction == NotSchoolActivity.KITCHEN) {
            note = view.findViewById(R.id.note);
            infoAditional = view.findViewById(R.id.infoAditional);
        } else if (optionAction == NotSchoolActivity.WALKERS) {
            ethnicGroup = view.findViewById(R.id.ethnicGroup);

        } else {
            ethnicGroup = view.findViewById(R.id.ethnicGroup);
            note = view.findViewById(R.id.note);
            infoAditional = view.findViewById(R.id.infoAditional);
        }

        if (familyCode != null) {
            headFamily.setVisibility(View.GONE);
            isHeadFamily = true;
        }

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

        pregnantbeneficiary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if (state) {
                    objectBeneficiary.setPregnant("SI");
                } else {
                    objectBeneficiary.setPregnant("NO");
                }
            }
        });

        headFamily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                isHeadFamily = state;
            }
        });

        if (selectItem == BeneficiaryActivity.EDIT) {
            add.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            titleForm.setText(getResources().getString(R.string.edit_beneficiary));
            agreement = objectBeneficiary.getAgreement();
            getUID = objectBeneficiary.getUid();
            id = objectBeneficiary.getId().toString();
            btnRecovery.setText(getResources().getString(R.string.update));
            setInfoForm();
            if (Integer.parseInt(Objects.requireNonNull(objectBeneficiary.getHouseholdCode())) == Integer.parseInt(Objects.requireNonNull(objectBeneficiary.getDocument()))) {
                add.setEnabled(true);
                delete.setEnabled(false);
            } else {
                add.setEnabled(false);
                delete.setEnabled(true);
            }

        } else {
            btnRecovery.setText(getResources().getString(R.string.registerbeneficiary));
            titleForm.setText(getResources().getString(R.string.create_beneficiary));
            getUID = "";
            id = "";
        }

    }

    private void setInfoForm() {

        namebeneficiary.setText(objectBeneficiary.getFirstName());
        seconenamebeneficiary.setText(objectBeneficiary.getSecondName());
        lastnamebeneficiary.setText(objectBeneficiary.getSurname());
        surnamebeneficiary.setText(objectBeneficiary.getSecondSurname());
        documentbeneficiary.setText(objectBeneficiary.getDocument());

        phonebeneficiary.setText(objectBeneficiary.getPhone());
        birthdatebeneficiary.setText(objectBeneficiary.getBirthDate());
        familyCode = objectBeneficiary.getHouseholdCode();

        if (objectBeneficiary.getNationality() != null) {
            nationalitybeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getNationality(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_NATIONALITY, "")));
        } else {
            nationalitybeneficiary.setText("");
        }

        if (objectBeneficiary.getDocumentType() != null) {
            documentTypebeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getDocumentType(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DOCUMENTS, "")));
        } else {
            documentTypebeneficiary.setText("");
        }

        if (objectBeneficiary.getGender() != null) {
            genderbeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getGender(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_GENDERS, "")));
            if (objectBeneficiary.getGender().equals(1)) {
                pregnantbeneficiary.setVisibility(View.VISIBLE);
            }
        } else {
            genderbeneficiary.setText("");
        }

        if (objectBeneficiary.getMaritalStatus() != null) {
            maritalStatus.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getMaritalStatus(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_MARITAL, "")));
        } else {
            maritalStatus.setText("");
        }

        if (objectBeneficiary.getMigratoryStatus() != null) {
            migratoryStatus.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getMigratoryStatus(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_MIGRATORY, "")));
        } else {
            migratoryStatus.setText("");
        }

        if (objectBeneficiary.getHouseholdRole() != null) {
            householdRoleBeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getHouseholdRole(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_HOUSESHOLD, "")));
        } else {
            householdRoleBeneficiary.setText("");
        }

        if (objectBeneficiary.getRecipient() != null) {
            recipientBeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getRecipient(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_RECIPIENT, "")));
        } else {
            recipientBeneficiary.setText("");
        }

        if (objectBeneficiary.getDisability() != null) {
            disabilitiesbeneficiary.setText(Utils.getInstance().findDataSpinner(objectBeneficiary.getDisability(), PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DISABILITIES, "")));
        } else {
            recipientBeneficiary.setText("");
        }

        if (objectBeneficiary.getPregnant() != null) {
            if (objectBeneficiary.getPregnant().equals("SI")) {
                pregnantbeneficiary.setChecked(true);
            } else {
                pregnantbeneficiary.setChecked(false);
            }
        }

        if (optionAction == NotSchoolActivity.KITCHEN) {
            note.setText(objectBeneficiary.getNote());
            infoAditional.setText(objectBeneficiary.getAditionalInformation());
        } else if (optionAction == NotSchoolActivity.WALKERS) {
            ethnicGroup.setText(objectBeneficiary.getEthnicity());
        } else if (optionAction == NotSchoolActivity.INKIND) {
            ethnicGroup.setText(objectBeneficiary.getEthnicity());
            note.setText(objectBeneficiary.getNote());
            infoAditional.setText(objectBeneficiary.getAditionalInformation());
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        //al pasar la fecha y dar ok se setea la fecha en el textview
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            int mont = month + 1;
            birthdatebeneficiary.setText(day + "/" + mont + "/" + year);
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
                if (validate()) {
                    RegisterBeneficiary registerBeneficiary = null;
                    if (familyCode == null) familyCode = documentbeneficiary.getText().toString();
                    if (optionAction == NotSchoolActivity.KITCHEN) {
                        //Validate fields
                        registerBeneficiary = new RegisterBeneficiary(
                                getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 1, 1, objectBeneficiary.getMigratoryStatus(),
                                objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                                surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), null, birthdatebeneficiary.getText().toString(),
                                objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                                familyCode, agreement, userID);
                    } else if (optionAction == NotSchoolActivity.WALKERS) {
                        registerBeneficiary = new RegisterBeneficiary(
                                getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 1, 1, objectBeneficiary.getMigratoryStatus(),
                                objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                                surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(),
                                objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), null, null,
                                familyCode, agreement, userID);
                    } else if (optionAction == NotSchoolActivity.INKIND) {
                        registerBeneficiary = new RegisterBeneficiary(
                                getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 1, 1, objectBeneficiary.getMigratoryStatus(),
                                objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                                surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(),
                                objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                                familyCode, agreement, userID);
                    }
                    mListener.setUploadBeneficiary(id, registerBeneficiary, selectItem, isHeadFamily);
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
                                if (data.getId().equals(1)) {
                                    pregnantbeneficiary.setVisibility(View.VISIBLE);
                                } else {
                                    pregnantbeneficiary.setVisibility(View.GONE);
                                }
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
                                objectBeneficiary.setDocumentType(data.getId());
                                documentTypebeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.maritalStatus:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_MARITAL, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setMaritalStatus(data.getId());
                                maritalStatus.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.migratoryStatus:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_MIGRATORY, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setMigratoryStatus(data.getId());
                                migratoryStatus.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.houseHoldRole:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_HOUSESHOLD, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setHouseholdRole(data.getId());
                                householdRoleBeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.recipientID:
                if (selectItem == BeneficiaryActivity.EDIT) {
                    recipientBeneficiary.setInputType(InputType.TYPE_NULL);
                } else {
                    SelectOptionDialog.newInstance(
                            PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_RECIPIENT, ""),
                            new SelectOptionDialog.onListenerInterface() {
                                @Override
                                public void optionSelect(Data data) {
                                    objectBeneficiary.setRecipient(data.getId());
                                    recipientBeneficiary.setText(data.getName());
                                }
                            }).show(getActivity().getSupportFragmentManager(), "");

                }
                break;
            case R.id.disabilities:
                SelectOptionDialog.newInstance(
                        PreferencesHelper.getPreference(getActivity(), PreferencesHelper.KEY_DISABILITIES, ""),
                        new SelectOptionDialog.onListenerInterface() {
                            @Override
                            public void optionSelect(Data data) {
                                objectBeneficiary.setDisability(data.getId());
                                disabilitiesbeneficiary.setText(data.getName());
                            }
                        }).show(getActivity().getSupportFragmentManager(), "");
                break;

            case R.id.btn_add:
                mListener.addMemberFamily(objectBeneficiary.getAgreement(), familyCode);
                break;

            case R.id.btn_delete:

                final EditText cause;
                final Button send;
                View v = getLayoutInflater().inflate(R.layout.delete_member, null);
                deleteDialog = new BottomSheetDialog(getContext());
                deleteDialog.setContentView(v);

                deletenamebeneficiary = v.findViewById(R.id.kitchen_name);
                deletenamebeneficiary.setText(objectBeneficiary.getFirstName()+" "+objectBeneficiary.getSurname());
                cause = v.findViewById(R.id.motivo);
                send = v.findViewById(R.id.btndelete);

                deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deleteDialog.show();

                cause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                        b.setTitle(getResources().getString(R.string.deletemember));
                        String[] types = getResources().getStringArray(R.array.homechange);
                        b.setItems(types, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if(which == 0){
                                    cause.setText(R.string.newhome);
                                    send.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            newHomeBeneficiary();
                                        }
                                    });

                                }else if(which == 1){
                                    cause.setText(R.string.died);
                                    send.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            deadBeneficiary();
                                        }
                                    });

                                }

                            }

                        });

                        b.show();
                    }
                });

                break;
            default:
                break;
        }
    }

    private void newHomeBeneficiary() {
        RegisterBeneficiary registerBeneficiary = null;
        if (optionAction == NotSchoolActivity.KITCHEN) {
            //Validate fields
            registerBeneficiary = new RegisterBeneficiary(
                    getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 1, 1, objectBeneficiary.getMigratoryStatus(),
                    objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                    surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), null, birthdatebeneficiary.getText().toString(),
                    objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                    documentbeneficiary.getText().toString(), agreement, userID);
        } else if (optionAction == NotSchoolActivity.WALKERS) {
            registerBeneficiary = new RegisterBeneficiary(
                    getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 1, 1, objectBeneficiary.getMigratoryStatus(),
                    objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                    surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(),
                    objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), null, null,
                    documentbeneficiary.getText().toString(), agreement, userID);
        } else if (optionAction == NotSchoolActivity.INKIND) {
            registerBeneficiary = new RegisterBeneficiary(
                    getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 1, 1, objectBeneficiary.getMigratoryStatus(),
                    objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                    surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(),
                    objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                    documentbeneficiary.getText().toString(), agreement, userID);
        }
        mListener.setUploadBeneficiary(id, registerBeneficiary, selectItem, isHeadFamily);

    }

    private void deadBeneficiary() {

        RegisterBeneficiary registerBeneficiary = null;
        if (optionAction == NotSchoolActivity.KITCHEN) {
            //Validate fields
            registerBeneficiary = new RegisterBeneficiary(
                    getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 4, 1, objectBeneficiary.getMigratoryStatus(),
                    objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                    surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), null, birthdatebeneficiary.getText().toString(),
                    objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                    familyCode.toString(), agreement, userID);
        } else if (optionAction == NotSchoolActivity.WALKERS) {
            registerBeneficiary = new RegisterBeneficiary(
                    getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 4, 1, objectBeneficiary.getMigratoryStatus(),
                    objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                    surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(),
                    objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), null, null,
                    familyCode.toString(), agreement, userID);
        } else if (optionAction == NotSchoolActivity.INKIND) {
            registerBeneficiary = new RegisterBeneficiary(
                    getUID, objectBeneficiary.getNationality(), objectBeneficiary.getDocumentType(), objectBeneficiary.getGender(), objectBeneficiary.getHouseholdRole(), objectBeneficiary.getRecipient(), 4, 1, objectBeneficiary.getMigratoryStatus(),
                    objectBeneficiary.getMaritalStatus(), objectBeneficiary.getDisability(), namebeneficiary.getText().toString(), seconenamebeneficiary.getText().toString(), lastnamebeneficiary.getText().toString(),
                    surnamebeneficiary.getText().toString(), documentbeneficiary.getText().toString(), ethnicGroup.getText().toString(), birthdatebeneficiary.getText().toString(),
                    objectBeneficiary.getPregnant(), phonebeneficiary.getText().toString(), infoAditional.getText().toString(), note.getText().toString(),
                    familyCode.toString(), agreement, userID);
        }
        mListener.setUploadBeneficiary(id, registerBeneficiary, selectItem, isHeadFamily);



    }


    private boolean validate() {

        boolean state = true;

        if (TextUtils.isEmpty(namebeneficiary.getText())) {
            tilnamebeneficiary.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }
        if (TextUtils.isEmpty(lastnamebeneficiary.getText())) {
            tillastnamebeneficiary.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }
        if (TextUtils.isEmpty(documentbeneficiary.getText())) {
            tildocumentbeneficiary.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }
        if (TextUtils.isEmpty(documentTypebeneficiary.getText())) {
            tildocumentTypebeneficiary.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }
        if (TextUtils.isEmpty(genderbeneficiary.getText())) {
            tilgenderbeneficiary.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }
        if (TextUtils.isEmpty(nationalitybeneficiary.getText())) {
            tilnationalitybeneficiary.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }

        if (TextUtils.isEmpty(migratoryStatus.getText())) {
            migratoryStatus.setError(getResources().getString(R.string.obligatorio));
            state = false;
        }

        return state;

    }

    public interface OnFragmentInteractionListener {
        void setUploadBeneficiary(String id, RegisterBeneficiary registerBeneficiary, int optionAction, boolean isHeadFamily);

        void addMemberFamily(String agreement, String familyCode);
    }
}
