package com.example.animal;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ValueActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        button= (Button) findViewById(R.id.button3);

    }

    public  void onClick(View view){
//        ValueAnimator animator=ValueAnimator.ofInt(0,100);
//        animator.setDuration(5000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Integer value= (Integer) animation.getAnimatedValue();
//                button.setText(value+"");
//            }
//        });
//        animator.start();
        PointF p1=new PointF(20,20);
        PointF p2=new PointF(300,300);
        ValueAnimator animator=ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF currentPointF=new PointF();
                currentPointF.x=startValue.x+fraction*(endValue.x-startValue.x);
                currentPointF.y=startValue.y+fraction*(endValue.y-startValue.y);
                return currentPointF;
            }
        },p1,p2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF p= (PointF) animation.getAnimatedValue();
                button.setX(p.x);
                button.setY(p.y);
            }
        });
        animator.setDuration(2000);
        animator.start();
    }
}
