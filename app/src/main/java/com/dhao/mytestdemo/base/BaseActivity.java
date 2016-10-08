package com.dhao.mytestdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dhao.eventbuslibrary.EventBus;
import com.dhao.mytestdemo.mvp.varyview.VaryViewHelpController;

import butterknife.ButterKnife;

/**
 * Created by DongHao on 2016/9/9.
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected VaryViewHelpController viewHelpController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if (isBindEventBus()){
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);

        if (getLoadingTargetView()!=null){
            viewHelpController=new VaryViewHelpController(getLoadingTargetView());
        }
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

    protected View getLoadingTargetView(){
        return null;
    }

}
