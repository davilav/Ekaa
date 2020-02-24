package com.pma.ekaa.ui.welcome;

import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;

public interface WelcomeView {

    void showLoading();
    void hideLoading();

    void getPartnersSuccess(ArrayList<Data> data);

    void responseError(String msg);
}
