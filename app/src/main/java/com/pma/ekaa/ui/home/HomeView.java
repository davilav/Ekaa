package com.pma.ekaa.ui.home;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;

import java.util.ArrayList;

public interface HomeView {

    void showLoading();
    void hideLoading();

    void getDepartmentSuccess(ArrayList<Data> data);
    void getTownSuccess(ArrayList<Data> data);
    void getInstitutionSuccess(ArrayList<Data> data);
    void getLogoutSuccess();

    void getModalitySuccess(ArrayList<Modality>  modality);

    void responseError(String msg);
}
