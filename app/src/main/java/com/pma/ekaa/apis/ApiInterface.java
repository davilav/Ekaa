package com.pma.ekaa.apis;

import android.content.ContentQueryMap;

import com.pma.ekaa.models.Beneficiary;
import com.pma.ekaa.models.Login;
import com.pma.ekaa.models.Register;
import com.pma.ekaa.models.RegisterBeneficiary;
import com.pma.ekaa.models.User;
import com.pma.ekaa.models.UserLog;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("rest-auth/login/")
    Call <UserLog> login(@Body Login login);

    @POST("rest-auth/logout/")
    Call<UserLog> login();

    @POST("rest-auth/registration/")
    Call<User> register(@Body Register register);

    @POST("api/v1/beneficiaries/ ")
    Call<Beneficiary> registerBeneficiary(@Body RegisterBeneficiary registerbeneficiary);

    @GET("api/v1/persons/")
    Call<ResponseBody> getToken(@Header("Authorization") String authToken);


}