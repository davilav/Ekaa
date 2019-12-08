package com.pma.ekaa.ui.student.presenter;

import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.RegisterStudent;

public interface StudentPresenter {

    void setUploadStudent(RegisterStudent registerStudent, int selectItem);

    void createStudentSuccess();
    void updateStudentSuccess();

    void responseError(String msg);

}
