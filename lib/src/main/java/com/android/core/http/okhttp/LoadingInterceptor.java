package com.android.core.http.okhttp;

import com.android.core.base.UIView;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bug on 2016/11/8.
 */
public class LoadingInterceptor implements Interceptor {
    private UIView uiView;
    public LoadingInterceptor(UIView uiView) {
        this.uiView=uiView;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        if(request!=null&&proceed==null){
            showLoading();
        }else if(proceed!=null){
            hideLoading();
        }
        return proceed;
    }

    private void hideLoading() {
        if(uiView.isExist())
        uiView.showLoadingView();
    }

    private void showLoading() {
        if(uiView.isExist())
        uiView.hideLoadingView();
    }
}
