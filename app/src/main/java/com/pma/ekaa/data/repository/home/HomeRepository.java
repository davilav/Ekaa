package com.pma.ekaa.data.repository.home;


public interface HomeRepository {

    void getDataDepartment();

    void getDataTown(int departmentID);

    void getDataInstitution(int townID);

    void getModalities();

    void setLogout();
}
