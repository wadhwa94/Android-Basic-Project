package com.example.jawadh.helloworld;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by jawadh on 27-09-2016.
 */
public class RestAPI {
    private final String API_KEY = "YWRtaW46cGFzc3dvcmQxMjM=";
    private final String API_BASE_URL = "http://httpbin.org/ip/";
    public AsyncHttpClient client;


    public RestAPI() {
        this.client = new AsyncHttpClient();
    }

    // http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=<key>
    public void getAllUsers(JsonHttpResponseHandler handler) {
        String url = getApiUrl("");
//        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, handler);
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }
}