package com.pma.ekaa.ui.student.presenter;

import com.pma.ekaa.data.models.RegisterBeneficiary;

public interface StudentPresenter {
    void setUploadStudent(String id , RegisterBeneficiary registerBeneficiary, int optionAction);

    void createStudentSuccess();
    void updateStudentSuccess();

    void responseError(String msg);

}
