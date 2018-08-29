package com.example.acer.moni2.com.bwie.view;

import com.example.acer.moni2.com.bwie.bean.CarBean;

/**
 * Created by acer on 2018/8/23.
 */

public interface CarView {
    void success(CarBean carBean);
    void failure(String s);
}
