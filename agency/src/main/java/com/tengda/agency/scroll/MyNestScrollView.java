package com.tengda.agency.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by DongHao on 2016/12/16.
 * Description:
 */

public class MyNestScrollView extends ScrollView {
    private scrollTopListener listener;

    public interface scrollTopListener {
        void scrollTop();
    }


    public MyNestScrollView(Context context) {
        this(context, null);
    }

    public MyNestScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListener(scrollTopListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int scrollY = getScrollY();
                Log.e("MyTest--->", "scrollY=" + scrollY);
                if (scrollY == 0) {
                    //允许父view进行事件拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                    listener.scrollTop();
                    Log.e("MyTest--->", "允许父view进行事件拦截");
                } else {
                    //禁止父view进行事件拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                    Log.e("MyTest--->", "禁止父view进行事件拦截");
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
