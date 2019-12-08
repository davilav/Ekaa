package com.pma.ekaa.ui.student;

public interface StudentView {

    void createStudentSuccess();
    void updateStudentSuccess();

    void responseError(String msg);

    void showLoading();
    void hideLoading();
}
