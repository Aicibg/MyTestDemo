package com.dhao.mytestdemo.eventbus;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dhao.eventbuslibrary.EventBus;
import com.dhao.eventbuslibrary.Subscribe;
import com.dhao.eventbuslibrary.meta.EventCenter;
import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.R;

import butterknife.BindView;

public class EventbusActivity extends BaseActivity {

    @BindView(R.id.btn_post)
    Button mBtnPost;
    @BindView(R.id.tv_name1)
    TextView mTvName1;
    @BindView(R.id.tv_age1)
    TextView mTvAge1;
    @BindView(R.id.tv_name2)
    TextView mTvName2;
    @BindView(R.id.tv_age2)
    TextView mTvAge2;

    @Override
    protected void initViews() {
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventCenter<>(123,new User("Jack","23")));
            }
        });
    }

    @Subscribe(tag = 123)
    public void getUser(EventCenter<User> eventCenter){
        mTvName1.setText(eventCenter.getData().getName());
        mTvAge1.setText(eventCenter.getData().getAge());
    }

    @Subscribe(tag = 1234)
    public void getUser2(EventCenter<User> eventCenter){
        mTvName2.setText(eventCenter.getData().getName());
        mTvAge2.setText(eventCenter.getData().getAge());
    }

    @Override
    protected boolean isBindEventBus() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_eventbus;
    }

}
