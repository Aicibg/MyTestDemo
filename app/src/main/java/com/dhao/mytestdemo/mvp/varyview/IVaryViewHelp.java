package com.dhao.mytestdemo.mvp.varyview;

import android.content.Context;
import android.view.View;

/**
 * Created by DongHao on 2016/9/26.
 * Description:
 */

public interface IVaryViewHelp {
    public abstract View getCurrentLayout();
    public abstract void restoreView();
    public abstract void showLayout(View view);
    public abstract View inflate(int layoutId);
    public abstract Context getContext();
    public abstract View getView();
}
