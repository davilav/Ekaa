package com.pma.ekaa.data.remote;

import com.pma.ekaa.BuildConfig;
import com.pma.ekaa.utils.Utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = Utils.getConnection().getIpAddress();
    private static ApiClient mInstance;
    private Retrofit retrofit;

    private ApiClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

    }

    public static synchronized ApiClient getInstance() {

        if (mInstance == null) {
            mInstance = new ApiClient();
        }

        return mInstance;
    }

    public ApiInterface getApi() {

        return retrofit.create(ApiInterface.class);

    }
}

