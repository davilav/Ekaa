package com.pma.ekaa.data.repository.home;

import com.pma.ekaa.data.models.RequestUser;

public interface HomeRepository {

    void getDataInstitutionByPartner(RequestUser requestUser);

    void getDataUser(RequestUser requestUser);

    void setLogout();
}
