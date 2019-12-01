package com.pma.ekaa.ui.school;

import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;

public interface SchoolView {

    void showLoading();
    void hideLoading();

    void getGroupSuccess(ArrayList<Data> data);
    void getClassSuccess(ArrayList<Data> data);

    void responseError(String msg);
}
