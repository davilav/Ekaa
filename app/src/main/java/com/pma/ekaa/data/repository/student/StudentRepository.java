package com.pma.ekaa.data.repository.student;

import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.RegisterStudent;

public interface StudentRepository {

    void setCreateStudent(RegisterStudent registerStudent);

    void setUpdateStudent(RegisterStudent registerStudent);
}
