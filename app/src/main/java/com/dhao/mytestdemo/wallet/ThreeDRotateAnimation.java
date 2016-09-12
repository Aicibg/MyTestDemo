package com.dhao.mytestdemo.wallet;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by DongHao on 2016/9/12.
 * Description:旋转动画
 */
public class ThreeDRotateAnimation extends Animation {
    int centerX,centerY;
    Camera camera=new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX=width/2;
        centerY=height/2;
        setDuration(500);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix=t.getMatrix();
        camera.save();
        //绕y轴旋转
        camera.rotateY(360*interpolatedTime);
        camera.getMatrix(matrix);
        //设置翻转中心点
        matrix.preTranslate(-centerX,-centerY);//在rotateY前平移
        matrix.postTranslate(centerX,centerY);//在rotateY后平移
        camera.restore();
    }
}
