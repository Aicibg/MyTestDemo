package com.dhao.mytestdemo.dagger2;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
public class DaggerPresenter {
    DaggerActivity activity;
    User user;

    public DaggerPresenter(DaggerActivity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    public void showUserName(){
        activity.showUserName(user.getUserName());
    }
}
