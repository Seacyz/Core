package com.android.demo;

import android.os.Bundle;

import com.android.core.base.BaseActivity;
import com.android.core.http.okhttp.RestApi;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by bug on 2016/11/8.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.content_main;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        RestApi.getInstance().create(ApiService.class).onUserLogin().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
