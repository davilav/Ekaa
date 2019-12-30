package com.pma.ekaa.ui.student.presenter;

import com.pma.ekaa.data.models.RegisterStudent;
import com.pma.ekaa.data.repository.student.StudentRepository;
import com.pma.ekaa.data.repository.student.StudentRepositoryImpl;
import com.pma.ekaa.ui.student.StudentActivity;
import com.pma.ekaa.ui.student.StudentView;

public class StudentPresenterImpl implements StudentPresenter {

    private StudentView view;
    private StudentRepository repository;

    public StudentPresenterImpl(StudentView view) {
        this.view = view;
        repository = new StudentRepositoryImpl(this);

    }

    @Override
    public void setUploadStudent(RegisterStudent registerStudent, int selectItem) {
        switch (selectItem) {
            case StudentActivity.CREATE:
                repository.setCreateStudent(registerStudent);
                break;
            case StudentActivity.EDIT:
                repository.setUpdateStudent(registerStudent);
                break;
        }
    }

    @Override
    public void createStudentSuccess() {
        view.createStudentSuccess();
    }

    @Override
    public void updateStudentSuccess() {
        view.updateStudentSuccess();
    }

    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }
}
