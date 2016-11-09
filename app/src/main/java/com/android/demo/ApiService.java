package com.android.demo;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bug on 2016/11/8.
 */
public interface ApiService {
    @GET("api/pdwadwa/login")
    Call<Response> onUserLogin();
}
