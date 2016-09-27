package com.dhao.mytestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.behavior.BehaviorActivity;
import com.dhao.mytestdemo.constraintlayout.ConstraintActivity;
import com.dhao.mytestdemo.dagger2.DaggerActivity;
import com.dhao.mytestdemo.iconfont.IconFontActivity;
import com.dhao.mytestdemo.mvp.LoadingMvpActivity;
import com.dhao.mytestdemo.mvp.LoginActivity;
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
    @BindView(R.id.tv_behavior)
    TextView tvBehavior;
    @BindView(R.id.tv_icon_font)
    TextView tvIconFont;
    @BindView(R.id.tv_constraint_layout)
    TextView tvConstraintLayout;
    @BindView(R.id.tv_mvp_loading)
    TextView tvMvpLoading;

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

//        mTvEventBus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, EventbusActivity.class));
//            }
//        });

        tvBehavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BehaviorActivity.class));
            }
        });

        tvIconFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IconFontActivity.class));
            }
        });

        tvConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConstraintActivity.class));
            }
        });

        tvMvpLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }



}
