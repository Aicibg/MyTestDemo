package com.dhao.mytestdemo.mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DongHao on 2016/9/26.
 * Description:
 */

public class VaryViewHelp implements IVaryViewHelp{
    private View view;
    private int viewIndex;
    private ViewGroup parentView;
    private ViewGroup.LayoutParams params;
    private View currentView;

    public VaryViewHelp(View view) {
        this.view = view;
    }

    private void init(){
        params=view.getLayoutParams();
        if (view.getParent()!=null){
            parentView= (ViewGroup) view.getParent();
        }else {
            parentView= (ViewGroup) view.getRootView().findViewById(android.R.id.content);
        }
        int count=parentView.getChildCount();
        for(int i=0;i<count;i++){
           if (view==parentView.getChildAt(i)){
               viewIndex=i;
               break;
           }
        }
        currentView=view;
    }

    @Override
    public View getCurrentLayout() {
        return currentView;
    }

    @Override
    public void restoreView() {
       showLayout(view);
    }

    @Override
    public void showLayout(View view) {
       if (parentView==null){
           init();
       }
        this.currentView=view;
        if (parentView.getChildAt(viewIndex)!=view){
            ViewGroup parent= (ViewGroup) view.getParent();
            if (parent!=null){
                parent.removeView(view);
            }
            parentView.removeViewAt(viewIndex);
            parentView.addView(view,viewIndex,params);
        }
    }

    @Override
    public View inflate(int layoutId) {
        return LayoutInflater.from(view.getContext()).inflate(layoutId,null);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public View getView() {
        return view;
    }
}
