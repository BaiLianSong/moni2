package com.example.acer.moni2.com.bwie.presenter;

import com.example.acer.moni2.com.bwie.bean.CarBean;
import com.example.acer.moni2.com.bwie.model.CarModel;
import com.example.acer.moni2.com.bwie.view.CarView;

import java.util.HashMap;

/**
 * Created by acer on 2018/8/23.
 */

public class CarPresenter {
    private CarModel carModel;
    private CarView carView;

    public CarPresenter(CarView carView) {
        carModel = new CarModel();
    }

    public void attachView(CarView carView){
        this.carView = carView;
    }

    public void getCarData(String url,HashMap<String , String> parms){
        carModel.getCarData(url, parms, new CarModel.CarCallBack() {
            @Override
            public void success(CarBean carBean) {
                if (carView!=null){
                    carView.success(carBean);
                }
            }

            @Override
            public void failure(String s) {
                if (carView!=null){
                    carView.failure(s);
                }
            }
        });
    }

    public void detachView(){
        this.carView = null;
    }
}
