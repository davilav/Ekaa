package com.pma.ekaa.ui.home;

import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.Modality;

import java.util.ArrayList;

public interface HomeView {

    void showLoading();
    void hideLoading();

    void getInstitutionByPartnerSuccess(ArrayList<InstitutionByPartner> data);
    void getDataUserSuccess(DataUser dataUser);
    void getLogoutSuccess();
    void getModalitySuccess(ArrayList<Modality>  modality);
    void responseError(String msg);
}
