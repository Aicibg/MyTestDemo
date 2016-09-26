package com.dhao.mytestdemo.iconfont;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DongHao on 2016/9/22.
 * Description:
 */

public class FontHelp {
    private static final String FONTD_DIR="fonts/";
    private static final String DEF_FONT=FONTD_DIR+ "iconfont.ttf";

    public static final void injectFont(View rootView){
        injectFont(rootView, Typeface.createFromAsset(rootView.getContext().getAssets(),DEF_FONT));
    }

    private static final void injectFont(View rootView, Typeface fromAsset) {
        if (rootView instanceof ViewGroup){
            ViewGroup view= (ViewGroup) rootView;
            int childCount=view.getChildCount();
            for(int i=0;i<childCount;i++){
                 injectFont(view.getChildAt(i),fromAsset);
            }
        }else if (rootView instanceof TextView){
            TextView textView= (TextView) rootView;
            textView.setTypeface(fromAsset);

        }
    }
}
