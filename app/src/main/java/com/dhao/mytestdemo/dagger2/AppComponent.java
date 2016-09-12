package com.dhao.mytestdemo.dagger2;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
@Singleton
@Component(modules = ApiModuel.class)
public interface AppComponent {
    OkHttpClient getClient();

    Retrofit getRetrofit();

    User getUser();
}
