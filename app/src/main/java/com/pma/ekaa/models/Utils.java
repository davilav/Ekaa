package com.pma.ekaa.models;

public class Utils {

    private static Utils INSTANCE = null;
    private static RequestUser obj;
    private static Geolocation object;
    private static AuthUser objectuser;



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

    public Geolocation getObject() {
        return object;
    }

    public void setObject(Geolocation object) {
        Utils.object = object;
    }

    public AuthUser getObjectuser() {
        return objectuser;
    }

    public void setObject(AuthUser objectuser) {
        Utils.objectuser = objectuser;
    }


}



