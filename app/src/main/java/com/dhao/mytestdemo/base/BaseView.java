package com.dhao.mytestdemo.base;

/**
 * Created by DongHao on 2016/9/27.
 * Description:
 */

public interface BaseView <T>{
    void setPresenter(T presenter);
    boolean isActive();
    void showProgress();
    void hideProgress();
    void showTip(String msg);
}
