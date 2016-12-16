package com.tengda.agency.viewdrag;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by DongHao on 2016/12/15.
 * Description:
 */

public class ViewDragMenuLayout extends LinearLayout {
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
    private boolean isOpen = false;


    public interface ViewDragListener {
        void onOpen();

        void onClose();

        void onDrag(float parcent);
    }

    public ViewDragMenuLayout(Context context) {
        this(context, null);
    }

    public ViewDragMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelpCallBack());
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), new XScrollDetector());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentLayoutWidth = mContentLayout.getMeasuredWidth();
        mBehindLayoutWidth = mBehindLayout.getMeasuredWidth();
    }

    @Override
    protected void onFinishInflate() {
        mContentLayout = (ViewGroup) getChildAt(0);
        mBehindLayout = (ViewGroup) getChildAt(1);
        mContentLayout.setClickable(true);
        mBehindLayout.setClickable(true);
        super.onFinishInflate();
    }

    private class XScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }
    }

    public class ViewDragHelpCallBack extends ViewDragHelper.Callback {
        /**
         * 根据返回结果决定当前child是否可以拖拽
         * @param child  当前被拖拽的view
         * @param pointerId  区分多点触摸的id
         * @return
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mContentLayout == child;
        }

        /**
         * 返回拖拽的范围，不会对拖拽进行真正的限制，仅仅决定了动画执行的速度
         * @param child
         * @return
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            return mContentLayoutWidth;
        }

        /**
         *
         * @param child
         * @param left   代表你将要移动的位置的坐标
         * @param dx
         * @return  返回值 最终确定的移动的位置
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.e("clampView()--->","left="+left);
            Log.e("clampView()--->","dx="+dx);
            if (child == mContentLayout) {
                int newLeft = Math.min(Math.max((-getPaddingLeft() - mBehindLayoutWidth), left), 0);
                Log.e("clampView()--->","newLeft="+newLeft);
                return newLeft;
            } else {
                int newLeft = Math.min(Math.max(left, getPaddingLeft() + mContentLayoutWidth - mBehindLayoutWidth),
                        (getPaddingLeft() + mContentLayoutWidth + getPaddingRight()));
                Log.e("clampView()--->","newLeft="+newLeft);
                return newLeft;
            }
        }

        /**
         * 位置改变时回调
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mViewDragRange = left;
            Log.e("onViewChanged()--->","mViewDragRange="+mViewDragRange);
            Log.e("onViewChanged()--->","top="+top);
            Log.e("onViewChanged()--->","dx="+dx);
            Log.e("onViewChanged()--->","dy="+dy);
            float parcent = Math.abs((float) left / (float) mContentLayoutWidth);
            if (mViewDragListener != null) {
                mViewDragListener.onDrag(parcent);
            }
            if (changedView == mContentLayout) {
                mBehindLayout.offsetLeftAndRight(dx);
            } else {
                mContentLayout.offsetLeftAndRight(dx);
            }
            invalidate();
        }

        /**
         * 拖动结束后回调
         * @param releasedChild
         * @param xvel  水平方向滑动速度 向右为正
         * @param yvel  垂直方向滑动速度 向下为正
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == mContentLayout) {
                if (xvel <= 0) {
                    //向左滑动  滑动距离大于右侧隐藏菜单宽度的1/2 小于总宽度 打开显示右侧隐藏菜单
                    if (-mViewDragRange >= mBehindLayoutWidth / 2 && -mViewDragRange <= mBehindLayoutWidth) {
                        open();
                    } else {
                        close();
                    }
                } else {
                    //向右滑动 滑动距离大于0小于右侧菜单宽度
                    if (-mViewDragRange >= 0 && -mViewDragRange <= mBehindLayoutWidth) {
                        close();
                    } else {
                        open();
                    }
                }
            }
        }
    }

    private void close() {
        if (mViewDragHelper.smoothSlideViewTo(mContentLayout, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (mViewDragListener != null) {
            mViewDragListener.onClose();
            isOpen = false;
        }
    }

    private void open() {
        if (mViewDragHelper.smoothSlideViewTo(mContentLayout, -mBehindLayoutWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (mViewDragListener != null) {
            mViewDragListener.onOpen();
            isOpen = true;
        }
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev) && mGestureDetectorCompat.onTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return false;
    }
}
