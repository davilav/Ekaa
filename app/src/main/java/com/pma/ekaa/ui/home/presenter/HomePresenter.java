package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.RequestUser;

import java.util.ArrayList;

public interface HomePresenter {

    void getDataDepartment(RequestUser requestUser);
    void getDataTown(int departmentID, RequestUser requestUser);
    void getDataInstitution(String partner, int townID, RequestUser requestUser);
    void getDataUser(RequestUser requestUser);

    void setLogout();
    void getModalities();
    void getDepartmentSuccess(ArrayList<Data> data);
    void getInstitutionSuccess(ArrayList<Data> data);

    void getTownSuccess(ArrayList<Data> data);

    void getDataUserSuccess(DataUser dataUser);

    void getLogoutSuccess();

    void getModalitySuccess(ArrayList<Modality> modality);

    void responseError(String msg);
}
