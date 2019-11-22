package com.pma.ekaa.data.repository.not_school;

import com.pma.ekaa.data.models.Attendance;

public interface NotSchoolRepository {

    void getListBeneficiary(String keyword, int page);

    void setRegisterAttendance(Attendance attendance);

}
