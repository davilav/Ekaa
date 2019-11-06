package com.pma.ekaa.ui.home;

import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;

import java.util.ArrayList;

public interface HomeView {

    void getInstitutionByPartnerSuccess(ArrayList<InstitutionByPartner> data);
    void getDataUserSuccess(DataUser dataUser);
    void getLogoutSuccess();
    void responseError(String msg);

}
