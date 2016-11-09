package com.android.core.http.ansyncHttp;

import android.support.annotation.NonNull;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.util.LinkedList;

public abstract class HttpClient {
    private static final AsyncHttpClient client = new AsyncHttpClient();

    private static final LinkedList<String> cancelledTagList = new LinkedList<>();

    public static AsyncHttpClient getClient() {
        return client;
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        if (handler instanceof GsonResponseHandler) {
            ((GsonResponseHandler) handler).setUrl(url);
        }
        addCommonParams(params);
        client.post(url, params, handler);
    }

    /**
     * 添加公共参数
     *
     * @param params
     */
    public static void addCommonParams(@NonNull RequestParams params) {
    }
}
