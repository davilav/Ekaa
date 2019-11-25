package com.pma.ekaa.ui.beneficiary;

public interface BeneficiaryView {

    void createBeneficiarySuccess();
    void updateBeneficiarySuccess();

    void responseError(String msg);

    void showLoading();
    void hideLoading();

}
