package com.android.core.http.ansyncHttp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.core.base.UIView;
import com.android.core.manage.Tip;
import com.android.core.manage.log.Logger;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
/**
 * Created by bug on 2015/09/23.
 */
public abstract class GsonResponseHandler<T> extends AsyncHttpResponseHandler {
    private static final String TAG = "GsonResponseHandler";

    private String url;

    private Class<T> clazz;
    private Context context;
    private UIView uiView;
    public GsonResponseHandler(Class<T> clazz) {
        this(clazz, null, null);
    }

    public GsonResponseHandler(Class<T> clazz, Context context) {
        this(clazz, context, null);
    }

    public GsonResponseHandler(Class<T> clazz, Context context, UIView uiView) {
        this.clazz = clazz;
        this.context = context;
        this.uiView=uiView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void onStart() {
        //super.onStart();
        Log.d(TAG, "onStart()");
        if (uiView.isExist())
        uiView.showLoadingView();
    }

    @Override
    public void onFinish() {
        //super.onFinish();
        Log.d(TAG, "onFinish()");
        if(uiView.isExist())
        uiView.hideLoadingView();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String response = new String(responseBody);

        if (TextUtils.isEmpty(response)) {
            bizFailed(response);
            return;
        }
        Log.e("response#! " + url, response);
        Logger.json(response);

            if(getStateCode(response)){
                Gson gson = new Gson();
                T t = gson.fromJson(response, clazz);
                succeed(t);
            }else {
                bizFailed(response);
            }
    }

    protected abstract void bizFailed(String response);

    protected abstract boolean getStateCode(String response);

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Log.e(TAG, "onFailure(): statusCode = " + statusCode + " " + url);
        networkFailed(statusCode + "");
    }
    public abstract void succeed(T t);
    public void networkFailed(String error) {
        Tip.show("请求服务器失败：" + error);
    }
}
