package com.dhao.mytestdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dhao.eventbuslibrary.EventBus;

import butterknife.ButterKnife;

/**
 * Created by DongHao on 2016/9/9.
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if (isBindEventBus()){
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        initViews();
    }

    protected boolean isBindEventBus(){
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract void initViews();

    protected abstract int getLayoutResId();

}
