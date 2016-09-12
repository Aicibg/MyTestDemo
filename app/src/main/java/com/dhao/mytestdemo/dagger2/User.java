package com.dhao.mytestdemo.dagger2;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
