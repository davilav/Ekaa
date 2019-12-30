package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;

import java.util.ArrayList;

public interface HomePresenter {

    void getDataDepartment();
    void getDataTown(int departmentID);
    void getDataInstitution(int townID);

    void setLogout();
    void getModalities();
    void getDepartmentSuccess(ArrayList<Data> data);
    void getInstitutionSuccess(ArrayList<Data> data);

    void getTownSuccess(ArrayList<Data> data);

    void getLogoutSuccess();

    void getModalitySuccess(ArrayList<Modality> modality);

    void responseError(String msg);
}
