package com.pma.ekaa.ui.welcome.presenter;

import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;

public interface WelcomePresenter {
    void getPartnersData();

    void getPartnersSuccess(ArrayList<Data> data);

    void responseError(String msg);
}
