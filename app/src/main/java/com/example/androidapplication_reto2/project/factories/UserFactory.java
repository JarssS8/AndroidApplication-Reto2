package com.example.androidapplication_reto2.project.factories;

import android.util.Xml;

import com.example.androidapplication_reto2.project.interfaces.RestUser;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;
import org.xmlpull.v1.XmlSerializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.XMLFormatter;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class UserFactory {

    private static String API_BASE_URL = "http://192.168.20.58:8080/ServerApplication-Reto2/webresources/user/";

    public static RestUser getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create()).client(client).build();

        RestUser restUser =  builder.create(RestUser.class);
        return restUser;
    }
}
