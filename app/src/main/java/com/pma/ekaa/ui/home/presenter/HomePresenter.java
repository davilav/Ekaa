package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.RequestUser;

import java.util.ArrayList;

public interface HomePresenter {

    void getDataInstitutionByPartner(RequestUser requestUser);
    void getDataUser(RequestUser requestUser);
    void setLogout();

    void getInstitutionByPartnerSuccess(ArrayList<InstitutionByPartner> data);
    void getDataUserSuccess(DataUser dataUser);
    void getLogoutSuccess();

    void responseError(String msg);

}