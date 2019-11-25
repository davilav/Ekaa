package com.pma.ekaa.utils;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Geolocation;
import com.pma.ekaa.data.models.RequestUser;
import com.pma.ekaa.data.models.UserLog;

import java.util.ArrayList;
import java.util.List;

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

    public String findDataSpinner(int id, String dataList){
        String response = "";
        ArrayList<Data> listData = new Gson().fromJson(dataList, new TypeToken<List<Data>>(){}.getType());
        for (Data data : listData) {
            if (data.getId() == id) {
                response = data.getName();
                break;
            }
        }
        return response;
    }

}



