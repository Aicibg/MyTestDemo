package com.dhao.mytestdemo.mvp.presenter;

import android.text.TextUtils;

import com.dhao.mytestdemo.mvp.contract.LoginContract;

/**
 * Created by DongHao on 2016/9/27.
 * Description:
 */

public class LoginPresenter implements LoginContract.Presenter {
    protected LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void login(String user, String password) {
         if (TextUtils.isEmpty(user)){
             view.showTip("用户名不能为空");
             return;
         }
        if (TextUtils.isEmpty(password)){
            view.showTip("密码不能为空");
            return;
        }
        view.showProgress();
        if (user.equals("donghao")&password.equals("123")){
            view.hideProgress();
            view.loginSuccess();
        }else {
            view.hideProgress();
            view.showTip("登录失败");
        }
    }
}
