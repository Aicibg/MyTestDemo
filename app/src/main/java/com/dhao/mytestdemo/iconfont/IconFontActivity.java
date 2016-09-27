package com.dhao.mytestdemo.iconfont;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.R;

import butterknife.BindView;

public class IconFontActivity extends BaseActivity {


    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.activity_icon_font)
    RelativeLayout activityIconFont;

    @Override
    protected void initViews() {
         // FontHelp.injectFont(findViewById(android.R.id.content));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_icon_font;
    }


}
