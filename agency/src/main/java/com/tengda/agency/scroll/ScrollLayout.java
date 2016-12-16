package com.tengda.agency.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by DongHao on 2016/12/16.
 * Description:
 */

public class ScrollLayout extends FrameLayout {
    private MyNestScrollView mContentLayout;
    private Scroller mScroller;
    int downX = 0;
    int downY = 0;
    private boolean isIntercept=false;
    private boolean isOnTop;


    public ScrollLayout(Context context) {
        this(context, null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        mContentLayout = (MyNestScrollView) getChildAt(0);
        mContentLayout.setListener(new MyNestScrollView.scrollTopListener() {
            @Override
            public void scrollTop() {
                isOnTop=true;
            }
        });
        super.onFinishInflate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y= (int) ev.getY();
        switch (ev.getAction()  ){
            case MotionEvent.ACTION_DOWN:
                downY=y;
                break;
              case MotionEvent.ACTION_MOVE:
                int dY=y-downY;
                  if (dY>0&&isOnTop){
                      isIntercept=true;
                  }else {
                      isIntercept=false;
                  }
                break;
              default:
                break;
        }
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e("MyTest-->", "x=" + x);
        Log.e("MyTest-->", "y=" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                Log.e("MyTest-->", "downX=" + downX);
                Log.e("MyTest-->", "downY=" + downY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnTop) {
                    downY = y;
                    isOnTop = false;
                }
                int dX = x - downX;
                int dY = y - downY;
                Log.e("MyTest-->", "dX=" + dX);
                Log.e("MyTest-->", "dY=" + dY);
                if (dY > 200) {
                    dY = 200;
                }
                if (dY<-200){
                    dY=-200;
                }
                Log.e("MyTest-->", "isIntercept="+isIntercept);
                if (dY>0&&isIntercept) {
                    scrollTo(0, -dY);
                    Log.e("MyTest-->", "执行了Scroll");
                }
                break;
            case MotionEvent.ACTION_UP:
                 scrollTo(0,0);
                isIntercept=false;
                break;
            default:
                break;
        }
        return true;
    }
}
