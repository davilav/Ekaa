package com.pma.ekaa.data.repository.student;

import com.pma.ekaa.data.models.RegisterBeneficiary;

public interface StudentRepository {

    void setCreateStudent(RegisterBeneficiary registerBeneficiary);

    void setUpdateStudent(String id, RegisterBeneficiary registerBeneficiary);
}
