package com.dhao.mytestdemo.scroller;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import com.dhao.mytestdemo.BaseActivity;
import com.dhao.mytestdemo.R;

import butterknife.BindView;

public class ScrollerTextActivity extends BaseActivity {

    @BindView(R.id.tv_scroll_view)
    ScrollerTextView mTvScrollView;

    @Override
    protected void initViews() {
//         mTvScrollView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//             @Override
//             public void onFocusChange(View v, boolean hasFocus) {
//                 if (hasFocus){
//                     mTvScrollView.gunDong();
//                 }
//             }
//         });
        mTvScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator animator=ValueAnimator.ofInt(mTvScrollView.getWidth());
                animator.setDuration(3000);
//                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
//                        mTvScrollView.gunDong();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                       mTvScrollView.scrollTo(0,0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float function=animation.getAnimatedFraction();
                        int dx= (int) (mTvScrollView.getWidth()*(1f-function));
                        int dt= (int) (3000*(1f-function));
                        mTvScrollView.scrollBy(dx/dt,0);
                    }
                });
              animator.start();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_scroller_text;
    }

}
