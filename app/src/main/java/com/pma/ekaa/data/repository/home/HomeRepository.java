package com.pma.ekaa.data.repository.home;

import com.pma.ekaa.data.models.RequestUser;

public interface HomeRepository {

    void getDataDepartment(RequestUser requestUser);

    void getDataTown(int departmentID, RequestUser requestUser);

    void getDataInstitution(String partner, int townID, RequestUser requestUser);

    void getDataUser(RequestUser requestUser);

    void getModalities();

    void setLogout();
}
