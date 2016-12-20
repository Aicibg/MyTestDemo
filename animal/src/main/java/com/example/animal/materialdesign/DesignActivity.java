package com.example.animal.materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animal.R;

public class DesignActivity extends AppCompatActivity {
    private ImageView ivHeart;
    private TextView btnState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ivHeart= (ImageView) findViewById(R.id.iv_heart);
        btnState= (TextView) findViewById(R.id.btn_state_anim);

        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view){
        int cX=(ivHeart.getRight()-ivHeart.getLeft())/2+ivHeart.getLeft();
        int cY=(ivHeart.getBottom()-ivHeart.getTop())/2+ivHeart.getTop();
        int finalRadius=Math.max(ivHeart.getWidth(),ivHeart.getHeight());
//        Animator animator= ViewAnimationUtils.createCircularReveal(ivHeart,cX,cY,0,finalRadius);
//        ivHeart.setVisibility(View.VISIBLE);
//        animator.start();
//        ObjectAnimator animator=ObjectAnimator.ofFloat(ivHeart,"")
        Animator animator= ViewAnimationUtils.createCircularReveal(ivHeart,cX,cY,finalRadius,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivHeart.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }
}
