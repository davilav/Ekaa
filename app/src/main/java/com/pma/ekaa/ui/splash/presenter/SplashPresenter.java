package com.pma.ekaa.ui.splash.presenter;

import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;

public interface SplashPresenter {
    void getData();

    void getNationalitySuccess(ArrayList<Data> nationality);

    void getDocumentsTypeSuccess(ArrayList<Data> documents);

    void getGendersSuccess(ArrayList<Data> genders);

    void getMaritalStatusSuccess(ArrayList<Data> maritalStatus);

    void getMigratorySuccess(ArrayList<Data> migratory);

    void getRecipientSuccess(ArrayList<Data> recipient);

    void loginError(String msg);
}
