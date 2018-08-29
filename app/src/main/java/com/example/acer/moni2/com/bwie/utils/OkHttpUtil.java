package com.example.acer.moni2.com.bwie.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by acer on 2018/8/23.
 */

public class OkHttpUtil {
    private static OkHttpUtil okHttpUtil;
    private OkHttpClient okHttpClient;

    public OkHttpUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtil getInstens(){
        if (okHttpUtil == null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil == null){
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    /*
    * get请求
    * */

    public void getData(String url, HashMap<String,String> parms, final RequestCallBack requestCallBack){
        StringBuilder stringBuilder = new StringBuilder();
        String s = "";

        for (Map.Entry<String,String> stringEntry:parms.entrySet()
             ) {
            stringBuilder.append("?").append(stringEntry.getKey()).append("=").append(stringEntry.getValue()).append("&");
        }

        s = url+stringBuilder.toString().substring(0,stringBuilder.length()-1);

        Request request = new Request.Builder()
                .url(s)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallBack!=null){
                    requestCallBack.failure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack!=null){
                    requestCallBack.success(call,response);
                }
            }
        });
    }

    /*
    * post请求
    * */
    public void postData(String url, HashMap<String,String> parms, final RequestCallBack requestCallBack){
        FormBody.Builder formBody = new FormBody.Builder();

        for (Map.Entry<String,String> stringEntry:parms.entrySet()
                ) {
            formBody.add(stringEntry.getKey(),stringEntry.getValue());
        }

        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallBack!=null){
                    requestCallBack.failure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack!=null){
                    requestCallBack.success(call,response);
                }
            }
        });
    }

    public interface RequestCallBack{
        void success(Call call, Response response);
        void failure(Call call, IOException e);
    }
}
