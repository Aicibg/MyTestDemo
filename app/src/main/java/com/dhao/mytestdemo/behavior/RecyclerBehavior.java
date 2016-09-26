package com.dhao.mytestdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DongHao on 2016/9/21.
 * Description:
 */

public class RecyclerBehavior extends CoordinatorLayout.Behavior<View>{

    public RecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int delta= (int) (dependency.getTranslationY()+dependency.getBottom());
        delta=delta-child.getTop();
        ViewCompat.offsetTopAndBottom(child,delta);
        return true;
    }
}
