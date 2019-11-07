package com.pma.ekaa.ui.not_school;

import com.pma.ekaa.data.models.BeneficiaryArray;

public interface NotSchoolView {
    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);
    void responseError(String msg);
}
