package com.pma.ekaa.ui.not_school.presenter;

import com.pma.ekaa.data.models.BeneficiaryArray;

public interface NotSchoolPresenter {
    void getListBeneficiary(String token, String keyword, int page);
    void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray);

    void responseError(String msg);

}
