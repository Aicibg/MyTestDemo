package com.dhao.mytestdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by DongHao on 2016/9/21.
 * Description:
 */

public class ButtonBehavior extends CoordinatorLayout.Behavior<View> {

    public ButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return target instanceof RecyclerView;
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy>0){
            if (child.getTranslationY()>-child.getHeight()){
                float trY=child.getTranslationY()-dy;
                if (trY<=-child.getHeight()){
                    trY=-child.getHeight();
                }
                consumed[1]= (int) (child.getTranslationY()-trY);
                child.setTranslationY(trY);
            }
        }else if (dy<0){
            if (child.getTranslationY()<0){
                float trY1=child.getTranslationY()-dy;
                if (trY1>=0){
                    trY1=0;
                }
                consumed[1]= (int) (child.getTranslationY()-trY1);
                child.setTranslationY(trY1);
            }
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(Math.abs(dependency.getTranslationY()));
        return true;
    }
}
