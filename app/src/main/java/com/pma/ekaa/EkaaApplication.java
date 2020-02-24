package com.pma.ekaa;

import android.app.Application;

import com.pma.ekaa.utils.Utils;

public class EkaaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.getInstance();
    }
}
