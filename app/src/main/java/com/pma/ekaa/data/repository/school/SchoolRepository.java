package com.pma.ekaa.data.repository.school;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.RegisterBeneficiary;

public interface SchoolRepository {

    void getListBeneficiary(String keyword, int page);
    void getAttendanceToday(int userID);
    void setRegisterAttendance(Attendance attendance);

    void setCreateBeneficiary(RegisterBeneficiary registerBeneficiary);

    void setUpdateBeneficiary(String id, RegisterBeneficiary registerBeneficiary);

    void getDataGroup();
    void getDataClass();

}
