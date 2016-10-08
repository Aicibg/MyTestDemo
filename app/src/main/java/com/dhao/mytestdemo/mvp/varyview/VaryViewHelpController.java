package com.dhao.mytestdemo.mvp.varyview;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhao.mytestdemo.R;
import com.dhao.mytestdemo.mvp.varyview.IVaryViewHelp;
import com.dhao.mytestdemo.mvp.varyview.VaryViewHelp;

/**
 * Created by DongHao on 2016/9/26.
 * Description:
 */

public class VaryViewHelpController {
    private IVaryViewHelp help;

    public VaryViewHelpController(View view) {
        this(new VaryViewHelp(view));
    }

    public VaryViewHelpController(IVaryViewHelp help) {
        this.help = help;
    }

    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener){
        View layout=help.inflate(R.layout.message);
        TextView textView= (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(emptyMsg)){
            textView.setText(emptyMsg);
        }
        ImageView imageView= (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.mipmap.ic_launcher);
        if (onClickListener!=null){
            layout.setOnClickListener(onClickListener);
        }
        help.showLayout(layout);
    }

    public void showLoading(String loadingMsg){
        View layout=help.inflate(R.layout.loading);
        TextView textView= (TextView) layout.findViewById(R.id.loading_info);
        if (!TextUtils.isEmpty(loadingMsg)){
            textView.setText(loadingMsg);
        }
        help.showLayout(layout);
    }

    public void restore(){
        help.restoreView();
    }
}
