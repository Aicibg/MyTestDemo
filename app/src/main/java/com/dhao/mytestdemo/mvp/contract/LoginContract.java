package com.dhao.mytestdemo.mvp.contract;

import com.dhao.mytestdemo.base.BasePresenter;
import com.dhao.mytestdemo.base.BaseView;

/**
 * Created by DongHao on 2016/9/27.
 * Description:
 */

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void loginSuccess();
    }

    interface Presenter extends BasePresenter{
        void login(String user,String password);
    }
}
