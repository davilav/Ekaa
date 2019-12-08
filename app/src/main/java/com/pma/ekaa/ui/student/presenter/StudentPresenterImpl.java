package com.pma.ekaa.ui.student.presenter;

import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.repository.beneficiary.BeneficiaryRepositoryImpl;
import com.pma.ekaa.data.repository.student.StudentRepository;
import com.pma.ekaa.data.repository.student.StudentRepositoryImpl;
import com.pma.ekaa.ui.beneficiary.BeneficiaryView;
import com.pma.ekaa.ui.student.StudentView;

public class StudentPresenterImpl implements StudentPresenter {

    private StudentView view;
    private StudentRepository repository;

    public StudentPresenterImpl(StudentView view) {
        this.view = view;
        repository = new StudentRepositoryImpl(this);

    }

    @Override
    public void setUploadStudent(String id, RegisterBeneficiary registerBeneficiary, int optionAction) {

    }

    @Override
    public void createStudentSuccess() {
        view.createStudentSuccess();
    }

    @Override
    public void updateStudentSuccess() {

    }

    @Override
    public void responseError(String msg) {

    }
}
