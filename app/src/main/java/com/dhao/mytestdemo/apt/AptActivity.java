package com.dhao.mytestdemo.apt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dhao.mytestdemo.R;
import com.dhao.mytestdemo.base.BaseActivity;
import com.example.GetMsg;

import butterknife.BindView;

@GetMsg(id = 2, name = "Jack")
public class AptActivity extends BaseActivity {


    @BindView(R.id.bt_apt)
    Button btApt;
    @BindView(R.id.tv_show_text)
    TextView tvShowText;

    @Override
    protected void initViews() {
        btApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 showText();
            }
        });
    }


    public void showText() {
          tvShowText.setText("能不能行？");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apt;
    }
}
