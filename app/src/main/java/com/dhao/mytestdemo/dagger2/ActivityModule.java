package com.dhao.mytestdemo.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
@Module
public class ActivityModule {
    private DaggerActivity activity;

    public ActivityModule(DaggerActivity activity) {
        this.activity = activity;
    }

    @Provides
    public DaggerActivity provideActivty(){
        return activity;
    }
//
//    @Provides
//    public User provideUser(){
//        return new User("user from ActivityModule");
//    }

    @Provides
    public DaggerPresenter provideDaggerPresenter(DaggerActivity activity,User user){
        return new DaggerPresenter(activity,user);
    }
}
