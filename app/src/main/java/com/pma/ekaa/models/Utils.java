package com.pma.ekaa.models;

public class Utils {

    private static Utils INSTANCE = null;
    private static RequestUser obj;

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

    public RequestUser getObj() {

        return obj;
    }
}



