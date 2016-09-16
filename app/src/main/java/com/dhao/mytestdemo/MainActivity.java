package com.dhao.mytestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dhao.mytestdemo.dagger2.DaggerActivity;
import com.dhao.mytestdemo.eventbus.EventbusActivity;
import com.dhao.mytestdemo.permission.PermissionActivity;
import com.dhao.mytestdemo.wallet.WalletAnimationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_animation)
    TextView tvAnimation;
    @BindView(R.id.tv_permission)
    TextView tvPermission;
    @BindView(R.id.tv_dagger)
    TextView tvDagger;
    @BindView(R.id.tv_event_bus)
    TextView mTvEventBus;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        tvAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WalletAnimationActivity.class));
            }
        });

        tvPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
            }
        });

        tvDagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaggerActivity.class));
            }
        });

        mTvEventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventbusActivity.class));
            }
        });
    }

}
