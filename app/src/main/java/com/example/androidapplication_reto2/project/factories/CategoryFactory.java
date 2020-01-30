package com.example.androidapplication_reto2.project.factories;

import com.example.androidapplication_reto2.project.interfaces.RestCategory;
import com.example.androidapplication_reto2.project.interfaces.RestUser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CategoryFactory {
        private static String API_BASE_URL = "http://192.168.20.112:8080/ServerApplication-Reto2/webresources/category/";

    /**
     * Get client from interact with the factory rest category
     * @return the client rest
     */
    public static RestCategory getClient(){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create()).client(client).build();

            RestCategory restCategory =  builder.create(RestCategory.class);
            return restCategory;
        }

}
