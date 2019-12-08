package com.pma.ekaa.data.repository.not_school;

import com.pma.ekaa.data.models.Attendance;

public interface NotSchoolRepository {

    void getListBeneficiary(String keyword, int page);
    void getAttendanceToday(int userID);
    void setRegisterAttendance(Attendance attendance);

}
