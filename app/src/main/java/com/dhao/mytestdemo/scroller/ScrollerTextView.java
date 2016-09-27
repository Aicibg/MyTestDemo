package com.dhao.mytestdemo.scroller;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by DongHao on 2016/9/20
 * Description:
 */

public class ScrollerTextView extends TextView{
    private Scroller mScroller;
    private boolean isScrolling;

    public ScrollerTextView(Context context) {
        this(context,null);
    }

    public ScrollerTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller=new Scroller(context);
        isScrolling=true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
//        if (isFocused()){
//            gunDong();
//            if (getScaleX()==getWidth()){
//                setScrollX(0);
//            }
//        }
    }

    public void gunDong(){
            int scrollX=getScrollX();
            mScroller.startScroll(scrollX,getScrollY(),getWidth(),0,4000);
            invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
