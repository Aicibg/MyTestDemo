package com.dhao.mytestdemo.dagger2;

import dagger.Component;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
@ActivityScope
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {
    void inJect(DaggerActivity daggerActivity);
}
