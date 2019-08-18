package com.pma.ekaa.apis;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.0.9:8000/";
    private static ApiClient mInstance;
    private  Retrofit retrofit;

    private ApiClient(){

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // <-- usamos el log level
                .build();


    }

    public static synchronized ApiClient getInstance(){

        if (mInstance == null){
            mInstance = new ApiClient();
        }

        return mInstance;
    }

    public ApiInterface getApi(){

        return retrofit.create(ApiInterface.class);

    }
}

