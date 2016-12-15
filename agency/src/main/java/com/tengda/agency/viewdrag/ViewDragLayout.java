package com.tengda.agency.viewdrag;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by DongHao on 2016/12/15.
 * Description:
 */

public class ViewDragLayout extends LinearLayout{
    //内容界面
    private ViewGroup mContentLayout;
    //遮盖界面
    private ViewGroup mBehindLayout;
    private ViewDragHelper mViewDragHelper;
    //手势事件类
    private GestureDetectorCompat mGestureDetectorCompat;
    //滑动距离
    private int mViewDragRange;
    //左滑最大距离
    private int mBehindLayoutWidth;
    //宽度
    private int mContentLayoutWidth;
    private ViewDragListener mViewDragListener;
    private boolean isOpen=false;


    public interface ViewDragListener{
        void onOpen();
        void onClose();
        void onDrag(float parcent);
    }

    public ViewDragLayout(Context context) {
        this(context,null);
    }

    public ViewDragLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


    public void setOnViewDragListener(ViewDragListener viewDragListener) {
        mViewDragListener = viewDragListener;
    }

    /**
     * 初始化
     */
    private void init() {
        mViewDragHelper=ViewDragHelper.create(this,1.0f,new ViewDragHelpCallBack());
        mGestureDetectorCompat=new GestureDetectorCompat(getContext(),new XScrollDetector());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentLayoutWidth=mContentLayout.getMeasuredWidth();
        mBehindLayoutWidth=mBehindLayout.getMeasuredWidth();
    }

    @Override
    protected void onFinishInflate() {
        mContentLayout= (ViewGroup) getChildAt(0);
        mBehindLayout= (ViewGroup) getChildAt(1);
        mContentLayout.setClickable(true);
        mBehindLayout.setClickable(true);
        super.onFinishInflate();
    }

    private class XScrollDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX)>Math.abs(distanceY);
        }
    }

    public class ViewDragHelpCallBack extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mContentLayout==child;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mContentLayoutWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child==mContentLayout){
                int newLeft=Math.min(Math.max((-getPaddingLeft()-mBehindLayoutWidth),left),0);
                return newLeft;
            }else {
                int newLeft=Math.min(Math.max(left,getPaddingLeft()+mContentLayoutWidth-mBehindLayoutWidth),
                        (getPaddingLeft()+mContentLayoutWidth+getPaddingRight()));
                return newLeft;
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mViewDragRange=left;
            float parcent=Math.abs((float)left/(float)mContentLayoutWidth);
            if (mViewDragListener!=null){
                mViewDragListener.onDrag(parcent);
                if (changedView==mContentLayout){
                    mBehindLayout.offsetLeftAndRight(dx);
                } else {
                mContentLayout.offsetLeftAndRight(dx);
            }
            }
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild==mContentLayout){
                if (xvel<=0){
                    //
                    if (-mViewDragRange>=mBehindLayoutWidth/2&&-mViewDragRange<=mBehindLayoutWidth){
                        open();
                    }else {
                        close();
                    }
                }else {
                    //
                    if (-mViewDragRange>=0&&-mViewDragRange<=mBehindLayoutWidth){
                        close();
                    }else {
                        open();
                    }
                }
            }
        }
    }

    private void close() {
        if (mViewDragHelper.smoothSlideViewTo(mContentLayout,0,0)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (mViewDragListener!=null){
            mViewDragListener.onClose();
            isOpen=false;
        }
    }

    private void open() {
        if (mViewDragHelper.smoothSlideViewTo(mContentLayout,-mBehindLayoutWidth,0)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (mViewDragListener!=null){
            mViewDragListener.onOpen();
            isOpen=true;
        }
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev)&&mGestureDetectorCompat.onTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return false;
    }
}
