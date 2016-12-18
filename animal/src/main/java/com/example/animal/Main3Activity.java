package com.example.animal;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private int[] res = {R.id.fab1, R.id.fab2, R.id.fab3, R.id.fab4, R.id.fab5};
    private List<FloatingActionButton> mFloatingActionButtons = new ArrayList<>();
    private boolean flag=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        for (int i = 0; i < res.length; i++) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(res[i]);
            floatingActionButton.setOnClickListener(this);
            mFloatingActionButtons.add(floatingActionButton);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab5:
                if (flag){
                    startAnim();
                }else {
                    closeAnim();
                }

                break;
            default:
                Toast.makeText(Main3Activity.this, "click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnim() {
        for (int i = 0; i < res.length-1; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mFloatingActionButtons.get(i), "translationY", 150 * (i+1),0f);
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*300);
            animator.start();
            flag=true;
        }
    }

    private void startAnim() {
        for (int i = 0; i < res.length-1; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mFloatingActionButtons.get(i), "translationY", 0f,150 * (i+1));
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*300);
            animator.start();
            flag=false;
        }
    }
}
