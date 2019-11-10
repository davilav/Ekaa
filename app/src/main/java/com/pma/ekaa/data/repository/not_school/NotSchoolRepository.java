package com.pma.ekaa.data.repository.not_school;

import com.pma.ekaa.data.models.Attendance;

public interface NotSchoolRepository {

    void getListBeneficiary(String token, String keyword, int page);

    void setRegisterAttendance(String token, Attendance attendance);

}
