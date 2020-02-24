package com.pma.ekaa.data.repository.beneficiary;

import com.pma.ekaa.data.models.RegisterBeneficiary;

public interface BeneficiaryRepository {

        void setCreateBeneficiary(RegisterBeneficiary registerBeneficiary);

        void setUpdateBeneficiary(String id, RegisterBeneficiary registerBeneficiary);
}
