package com.example.androidapplication_reto2.project.factories;

import com.example.androidapplication_reto2.project.interfaces.RestUser;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class UserFactory {

    private static String API_BASE_URL = "http://192.168.20.91:8080/ServerApplication-Reto2/webresources/";

    public static RestUser getClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();

        RestUser restUser =  retrofit.create(RestUser.class);
        return restUser;
    }
}
