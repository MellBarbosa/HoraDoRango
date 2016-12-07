package com.projeto.horadorango.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mell on 01/12/2016.
 */

public class ApiComunicator {

    public static final String API_BASE_URL = "http://www.horadorangooo.com.br/index.php/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    private static ApiService createService() {
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(ApiService.class);
    }

    private static ApiService api = null;

    public static ApiService get(){
        if(api == null){
            api = createService();
        }
        return api;
    }

}

