package com.pma.ekaa.utils;

import com.pma.ekaa.data.models.Geolocation;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.models.UserLog;

public class Utils {

    private static Utils INSTANCE = null;
    private static UserLog dataUser;
    private static Geolocation geolocation;

    public Utils() { }

    public static Utils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }
        return INSTANCE;
    }

    public UserLog getDataUser() {
        return dataUser;
    }

    public void setDataUser(UserLog dataUser) {
        Utils.dataUser = dataUser;
    }

    public Geolocation getGeolocation() { return geolocation; }

    public void setGeolocation(Geolocation object) {
        Utils.geolocation = object;
    }

}



