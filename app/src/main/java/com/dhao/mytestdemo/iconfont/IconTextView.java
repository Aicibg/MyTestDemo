package com.dhao.mytestdemo.iconfont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DongHao on 2016/9/22.
 * Description:
 */

public class IconTextView extends TextView{

    private static final String FONTD_DIR="fonts/";
    private static final String DEF_FONT=FONTD_DIR+ "iconfont.ttf";

    public IconTextView(Context context) {
        this(context,null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(),DEF_FONT));
    }
}
