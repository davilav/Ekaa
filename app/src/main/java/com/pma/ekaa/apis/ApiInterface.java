package com.pma.ekaa.apis;

import com.pma.ekaa.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("v1/user/1")
    Call<ArrayList<User>> getUsers();


}