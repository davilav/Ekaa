package com.pma.ekaa.utils;

import com.pma.ekaa.data.models.Geolocation;
import com.pma.ekaa.data.models.RequestUser;

public class Utils {

    private static Utils INSTANCE = null;
    private static RequestUser obj;
    private static Geolocation object;

    public Utils() {

    }

    public static Utils getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }

        return INSTANCE;
    }

    public void setObj(RequestUser obj1) {
        obj = obj1;
    }

    public RequestUser getObj() { return obj; }

    public Geolocation getObject() { return object; }

    public void setObject(Geolocation object) {
        Utils.object = object;
    }
}



