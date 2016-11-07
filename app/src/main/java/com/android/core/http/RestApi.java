package com.android.core.http;

import android.text.TextUtils;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    private boolean isDebug = false;
    private RestApi() {}
    public static  RestApi getInstance() {
       return RestApiHolder.mInstance;
    }
    private static class RestApiHolder{
        private static final RestApi mInstance=new RestApi();
    }

    public void init(boolean isDebug) {
        this.isDebug = isDebug;
    }

    private Retrofit createApiClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createOkHttpClient(isDebug))
                .build();
    }

    public <T> T create(String baseUrl, Class<T> clz) {
        String service_url = "";
        try {
            Field field1 = clz.getField("BASE_URL");
            service_url = (String) field1.get(clz);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return createApiClient(
                TextUtils.isEmpty(service_url) ? baseUrl : service_url).create(clz);
    }

    public <T> T create(Class<T> clz) {
        String service_url = "";
        try {
            Field field1 = clz.getField("BASE_URL");
            service_url = (String) field1.get(clz);
            if (TextUtils.isEmpty(service_url)) {
                throw new NullPointerException("base_url is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createApiClient(service_url).create(clz);
    }

    OkHttpClient createOkHttpClient(boolean debug) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .addInterceptor(
                        new HttpLoggingInterceptor().setLevel(
                                debug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();
    }
}
