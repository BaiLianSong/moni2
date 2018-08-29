package com.example.acer.moni2.com.bwie.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.acer.moni2.com.bwie.bean.CarBean;
import com.example.acer.moni2.com.bwie.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by acer on 2018/8/23.
 */

public class CarModel {
    Handler handler = new Handler();

    public void getCarData(String url, HashMap<String,String> parms, final CarCallBack carCallBack){
        OkHttpUtil.getInstens().postData(url, parms, new OkHttpUtil.RequestCallBack() {
            @Override
            public void success(Call call, Response response) {
                try {
                    String result = response.body().string();
                    if (!TextUtils.isEmpty(result)){
                        parseResult(result,carCallBack);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(Call call, IOException e) {
                if (carCallBack!=null){
                    carCallBack.failure("网络错误,请求失败");
                }
            }
        });

    }

    private void parseResult(String result, final CarCallBack carCallBack) {
        final CarBean gson = new Gson().fromJson(result,CarBean.class);

        if (carCallBack!=null&&gson!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    carCallBack.success(gson);
                }
            });
        }
    }

    public interface CarCallBack{
        void success(CarBean carBean);
        void failure(String s);
    }
}
