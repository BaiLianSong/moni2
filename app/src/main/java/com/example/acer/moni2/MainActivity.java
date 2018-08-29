package com.example.acer.moni2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.acer.moni2.com.bwie.bean.CarBean;
import com.example.acer.moni2.com.bwie.presenter.CarPresenter;
import com.example.acer.moni2.com.bwie.view.CarView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CarView{
    private CarPresenter carPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        lodeDate();
    }

    private void lodeDate() {
        HashMap hashMap = new HashMap();
        hashMap.put("uid","71");

        carPresenter = new CarPresenter(this);
        carPresenter.getCarData("http://www.zhaoapi.cn/product/getCarts",hashMap);
    }

    private void initView() {
    }

    @Override
    public void success(CarBean carBean) {

        Toast.makeText(this,"请求成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
