package com.dhao.mytestdemo.wallet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.dhao.mytestdemo.BaseActivity;
import com.dhao.mytestdemo.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class WalletAnimationActivity extends BaseActivity {

    @BindView(R.id.coin_iv)
    CircleImageView coinIv;
    @BindView(R.id.wallet_iv)
    TextView walletIv;
    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    protected void initViews() {
       btnStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startCoin();
               setWallet();
           }
       });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet_animation;
    }

    private void startCoin(){
        //掉落
        Animation animationTranslate= AnimationUtils.loadAnimation(this,R.anim.translate);
        //旋转
        ThreeDRotateAnimation animation3D=new ThreeDRotateAnimation();
        animation3D.setRepeatCount(10);//重复次数

        AnimationSet animationSet=new AnimationSet(true);
        animationSet.setDuration(800);
        animationSet.addAnimation(animation3D);
        animationSet.addAnimation(animationTranslate);
        coinIv.startAnimation(animationSet);
    }

    private void setWallet(){
        final ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(800);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //当前动画完成的百分比
                float fraction=animation.getAnimatedFraction();
                //大概掉落到钱包的上边缘位置的时候，取消ValueAnimation动画，并执行钱包反弹效果
                if (fraction>=0.75){
                    valueAnimator.cancel();
                    startWallet();
                }
            }
        });
        valueAnimator.start();
    }

    private void startWallet() {
        //x轴缩放
        ObjectAnimator objectAnimation1=ObjectAnimator.ofFloat(walletIv,"scaleX",1,1.1f,0.9f,1);
        objectAnimation1.setDuration(600);
        //y轴缩放
        ObjectAnimator objectAnimation2=ObjectAnimator.ofFloat(walletIv,"scaleX",1,0.75f,1.25f,1);
        objectAnimation2.setDuration(600);

        AnimatorSet animatorset=new AnimatorSet();
        animatorset.setInterpolator(new LinearInterpolator());
        //同时执行x,y轴缩放动画
        animatorset.playTogether(objectAnimation1,objectAnimation2);
        animatorset.start();
    }

}
