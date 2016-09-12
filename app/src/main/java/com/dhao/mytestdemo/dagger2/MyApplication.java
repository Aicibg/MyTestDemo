package com.dhao.mytestdemo.dagger2;

import android.app.Application;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
public class MyApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=DaggerAppComponent.builder()
                .apiModuel(new ApiModuel())
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
