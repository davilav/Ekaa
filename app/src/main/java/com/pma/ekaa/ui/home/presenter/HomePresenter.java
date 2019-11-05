package com.pma.ekaa.ui.home.presenter;

import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.RequestUser;

import java.util.ArrayList;

public interface HomePresenter {

    void getDataInstitutionByPartner(RequestUser requestUser);
    void getDataUser(RequestUser requestUser);

    void getInstitutionByPartnerSuccess(ArrayList<InstitutionByPartner> data);
    void responseError(String msg);

}
