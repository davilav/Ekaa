package com.pma.ekaa.data.repository.home;


public interface HomeRepository {

    void getDataDepartment(int partnerID);

    void getDataTown(int partnerID, int departmentID);

    void getDataInstitution(int townID);

    void getModalities();

    void setLogout();
}
