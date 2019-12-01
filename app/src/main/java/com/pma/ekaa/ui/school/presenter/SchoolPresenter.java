package com.pma.ekaa.ui.school.presenter;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;

import java.util.ArrayList;

public interface SchoolPresenter {

    void getDataGroup();
    void getDataClass();


    void getGroupSuccess(ArrayList<Data> data);
    void getClassSuccess(ArrayList<Data> data);

    void responseError(String msg);
}
