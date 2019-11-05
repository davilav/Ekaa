package com.pma.ekaa.data.remote;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.Login;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Register;
import com.pma.ekaa.data.models.RegisterBeneficiary;
import com.pma.ekaa.data.models.Result;
import com.pma.ekaa.data.models.User;
import com.pma.ekaa.data.models.UserLog;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<Result> registerBeneficiary(@Body RegisterBeneficiary registerbeneficiary , @Header("Authorization") String authToken);

    @GET("rest-auth/login/")
    Call<ResponseBody> getKey(@Header("Authorization") String authToken);

    @GET("/api/v1/beneficiaries/")
    Call<BeneficiaryArray> listBeneficiary(
            @Header("Authorization") String authToken,
            @Query("q") String keyword,
            @Query("page") int page);

    @POST("/api/v1/attendances/")
    Call<Attendance> registerAttendance(@Body Attendance attendance,@Header("Authorization") String authToken);

    @GET("api/v1/institutions/partner/1")
    Call<ArrayList<InstitutionByPartner>> getInstitutions(@Header("Authorization") String authToken);

    @GET("rest-auth/user/")
    Call<DataUser> getDataUser(@Header("Authorization") String authToken);


}