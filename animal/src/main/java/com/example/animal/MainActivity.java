package com.example.animal;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btStart;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btStart = (Button) findViewById(R.id.button);
        mImageView = (ImageView) findViewById(R.id.imageView1);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360f).setDuration(1000).start();
//                ObjectAnimator.ofFloat(mImageView, "translationX", 0, 200f).setDuration(1000).start();
//                ObjectAnimator.ofFloat(mImageView, "translationY", 0, 200f).setDuration(1000).start();
//                PropertyValuesHolder propertyValuesHolder1=PropertyValuesHolder.ofFloat("rotation",0,360f);
//                PropertyValuesHolder propertyValuesHolder2=PropertyValuesHolder.ofFloat("translationX",0,200f);
//                PropertyValuesHolder propertyValuesHolder3=PropertyValuesHolder.ofFloat("translationY",0,200f);
//                ObjectAnimator.ofPropertyValuesHolder(mImageView,propertyValuesHolder1,propertyValuesHolder2,
//                        propertyValuesHolder3).setDuration(1000).start();
                ObjectAnimator animator1=ObjectAnimator.ofFloat(mImageView,"rotation",0,360f).setDuration(1000);
                ObjectAnimator animator2=ObjectAnimator.ofFloat(mImageView,"translationX",0,200f).setDuration(1000);
                ObjectAnimator animator3=ObjectAnimator.ofFloat(mImageView,"translationY",0,200f).setDuration(1000);
                AnimatorSet animatorSet=new AnimatorSet();
//                animatorSet.playSequentially(animator1,animator2,animator3);
                animatorSet.play(animator2).with(animator3);
                animatorSet.play(animator1).after(animator2);
                animatorSet.setDuration(1000);
                animatorSet.start();
            }
        });
    }
}
