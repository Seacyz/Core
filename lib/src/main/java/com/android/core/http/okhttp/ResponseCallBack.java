package com.android.core.http.okhttp;

import com.android.core.manage.ContextConfig;
import com.android.core.manage.Tip;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bug on 2015/09/23.
 */
public abstract class ResponseCallBack<T> implements Callback<T>{

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(getStateCode(response)){
            onSuccess(call,response);
        }else {
            onbizFailed(call,response);
        }
    }
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        networkFailed(t.getLocalizedMessage() + "");
    }
    public void networkFailed(String error){
        Tip.show(ContextConfig.HTTPFAILTOAST);
    }
    public abstract void onSuccess(Call<T> call, Response<T> response);
    public boolean getStateCode(Response<T> response){
        return true;
    }
    public void onbizFailed(Call<T> call, Response<T> response) {
    }
}
