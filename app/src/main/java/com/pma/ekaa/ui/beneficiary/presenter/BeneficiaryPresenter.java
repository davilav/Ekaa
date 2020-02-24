package com.pma.ekaa.ui.beneficiary.presenter;

import com.pma.ekaa.data.models.RegisterBeneficiary;

public interface BeneficiaryPresenter {
    void setUploadBeneficiary(String id ,RegisterBeneficiary registerBeneficiary, int optionAction);

    void createBeneficiarySuccess();
    void updateBeneficiarySuccess();

    void responseError(String msg);
}
