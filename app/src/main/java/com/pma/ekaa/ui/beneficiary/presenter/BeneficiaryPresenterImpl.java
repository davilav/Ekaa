package com.pma.ekaa.ui.beneficiary.presenter;

import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.repository.beneficiary.BeneficiaryRepository;
import com.pma.ekaa.data.repository.beneficiary.BeneficiaryRepositoryImpl;
import com.pma.ekaa.ui.beneficiary.BeneficiaryActivity;
import com.pma.ekaa.ui.beneficiary.BeneficiaryView;

public class BeneficiaryPresenterImpl implements BeneficiaryPresenter {

    private BeneficiaryView view;
    private BeneficiaryRepository repository;

    public BeneficiaryPresenterImpl(BeneficiaryView view) {
        this.view = view;
        repository = new BeneficiaryRepositoryImpl(this);

    }

    @Override
    public void setUploadBeneficiary(String id ,RegisterBeneficiary registerBeneficiary, int optionAction) {
        switch (optionAction){
            case BeneficiaryActivity.CREATE:
                repository.setCreateBeneficiary(registerBeneficiary);
                break;
            case BeneficiaryActivity.EDIT:
                repository.setUpdateBeneficiary(id, registerBeneficiary);
                break;
        }
    }

    @Override
    public void createBeneficiarySuccess() {
        view.createBeneficiarySuccess();
    }

    @Override
    public void updateBeneficiarySuccess() {
        view.updateBeneficiarySuccess();
    }

    @Override
    public void responseError(String msg) {

    }
}
