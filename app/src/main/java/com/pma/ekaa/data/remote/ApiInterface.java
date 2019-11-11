package com.pma.ekaa.data.remote;

import com.pma.ekaa.data.models.Attendance;
import com.pma.ekaa.data.models.BeneficiaryArray;
import com.pma.ekaa.data.models.Data;
import com.pma.ekaa.data.models.DataUser;
import com.pma.ekaa.data.models.Email;
import com.pma.ekaa.data.models.InstitutionByPartner;
import com.pma.ekaa.data.models.Login;
import com.pma.ekaa.data.models.Modality;
import com.pma.ekaa.data.models.Password;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @PUT("api/v1/beneficiaries/{id}/")
    Call<Result> updateBeneficiary(@Path("id") String id, @Body RegisterBeneficiary registerbeneficiary , @Header("Authorization") String authToken);

    @GET("/api/v1/modalities/")
    Call<ArrayList<Modality>> getModality(@Header("Authorization") String authToken);

    @GET("/api/v1/nationalities/")
    Call<ArrayList<Data>> getNationality();

    @GET("/api/v1/documentstype/")
    Call<ArrayList<Data>> getDocuments();

    @GET("/api/v1/genders/")
    Call<ArrayList<Data>> getGenders();

    @GET("/api/v1/maritalstatus/")
    Call<ArrayList<Data>> getMaritalStatus();

    @GET("/api/v1/migratorystatus/")
    Call<ArrayList<Data>> getMigratoryStatus();

    @GET("/api/v1/recipients/")
    Call<ArrayList<Data>> getRecipients();


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

    @POST("rest-auth/password/reset/")
    Call <Email> password(@Body Password password);


}