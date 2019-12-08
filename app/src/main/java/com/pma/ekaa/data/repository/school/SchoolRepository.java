package com.pma.ekaa.data.repository.school;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.RegisterStudent;

public interface SchoolRepository {

    void getListBeneficiary(String keyword, int page, int idSchool, int idGroup);
    void getAttendanceToday(int userID);
    void setRegisterAttendance(Attendance attendance);

    void setCreateBeneficiary(RegisterStudent registerStudent);

    void setUpdateBeneficiary(String id, RegisterStudent registerStudent);

    void getDataGroup();
    void getDataClass();

}
