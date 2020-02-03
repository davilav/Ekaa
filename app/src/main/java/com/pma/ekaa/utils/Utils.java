package com.pma.ekaa.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pma.ekaa.data.models.Connection;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.Geolocation;
import com.pma.ekaa.data.models.UserLog;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static Utils instance = null;
    private static UserLog dataUser;
    private static Geolocation geolocation;
    private static Connection connection;

    public Utils() {
        //Utils
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public UserLog getDataUser() {
        return dataUser;
    }

    public static void setDataUser(UserLog object) {
        dataUser = object;
    }

    public Geolocation getGeolocation() { return geolocation; }

    static void setGeolocation(Geolocation object) {
        geolocation = object;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Utils.connection = connection;
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



