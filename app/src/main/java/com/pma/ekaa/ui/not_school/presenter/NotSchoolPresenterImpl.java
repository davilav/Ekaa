package com.pma.ekaa.ui.not_school.presenter;

import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.repository.not_school.NotSchoolRepository;
import com.pma.ekaa.data.repository.not_school.NotSchoolRepositoryImpl;
import com.pma.ekaa.ui.not_school.NotSchoolView;

public class NotSchoolPresenterImpl implements NotSchoolPresenter {

    private NotSchoolView view;
    private NotSchoolRepository repository;

    public NotSchoolPresenterImpl(NotSchoolView view) {
        this.view = view;
        repository = new NotSchoolRepositoryImpl(this);
    }

    @Override
    public void getListBeneficiary(String token, String keyword, int page) {
        repository.getListBeneficiary(token, keyword, page);
    }

    @Override
    public void getListBeneficiarySuccess(BeneficiaryArray beneficiaryArray) {
        view.getListBeneficiarySuccess(beneficiaryArray);
    }

    @Override
    public void responseError(String msg) {
        view.responseError(msg);
    }
}
