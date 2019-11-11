package com.pma.ekaa.ui.splash.presenter;

import android.app.Application;

import com.google.gson.Gson;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.repository.splash.SplashRepository;
import com.pma.ekaa.data.repository.splash.SplashRepositoryImpl;
import com.pma.ekaa.ui.BaseActivity;
import com.pma.ekaa.ui.splash.SplashView;
import com.pma.ekaa.utils.PreferencesHelper;

import java.util.ArrayList;

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;
    private SplashRepository repository;
    private Application application;

    public SplashPresenterImpl(SplashView view, Application application) {
        this.view = view;
        this.application = application;
        repository = new SplashRepositoryImpl(this);
    }

    @Override
    public void getData() {
        repository.getData();
    }

    @Override
    public void getNationalitySuccess(ArrayList<Data> nationality) {
        PreferencesHelper.setPreference(application, PreferencesHelper.KEY_NATIONALITY, new Gson().toJson(nationality));
        repository.getDocumentsType();
    }

    @Override
    public void getDocumentsTypeSuccess(ArrayList<Data> documents) {
        PreferencesHelper.setPreference(application, PreferencesHelper.KEY_DOCUMENTS, new Gson().toJson(documents));
        repository.getGenders();
    }

    @Override
    public void getGendersSuccess(ArrayList<Data> genders) {
        PreferencesHelper.setPreference(application, PreferencesHelper.KEY_GENDERS, new Gson().toJson(genders));
        repository.getMaritalStatus();
    }

    @Override
    public void getMaritalStatusSuccess(ArrayList<Data> maritalStatus) {
        PreferencesHelper.setPreference(application, PreferencesHelper.KEY_MARITAL, new Gson().toJson(maritalStatus));
        repository.getMigratoryStatus();
    }

    @Override
    public void getMigratorySuccess(ArrayList<Data> migratory) {
        PreferencesHelper.setPreference(application, PreferencesHelper.KEY_MIGRATORY, new Gson().toJson(migratory));
        repository.getRecipient();
    }

    @Override
    public void getRecipientSuccess(ArrayList<Data> recipient) {
        PreferencesHelper.setPreference(application, PreferencesHelper.KEY_MIGRATORY, new Gson().toJson(recipient));
        view.getDataSuccess();
    }

    @Override
    public void loginError(String msg) {
        view.loginError(msg);
    }

}
