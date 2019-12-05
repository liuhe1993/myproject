package com.eric.application1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class AnimationView extends View {

    //常量
    public static final float RADIUS = 50f;

    //当前Point，记录当前动画的值（x,y坐标）
    private Point curPoint;

    //画笔
    private Paint mPaint;

    //Java代码实例化View时调用
    public AnimationView(Context context) {
        super(context);
    }
    //XML文件实例时调用
    public AnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(@android.support.annotation.NonNull Canvas canvas) {
        //第一次绘制时
        if (curPoint == null){
            //初始化坐标为(50f, 50f)
            curPoint = new Point(RADIUS,RADIUS);
            //画圆
            drawCircle(canvas);
            //开始动画
            startAnimation();
        }else {//非第一次绘制
            drawCircle(canvas);
        }
    }

    //在当前坐标处绘制一个半径为50f的圆
    private void drawCircle(Canvas canvas) {
        float x = curPoint.getX();
        float y = curPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    //开始动画
    private void startAnimation() {
        //设置 起始值&结束值
        Point startPoint = new Point(RADIUS,RADIUS);//起始为左上角(50f,50f)
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);//终点为右下角(屏幕宽度-50f,屏幕高度-50f)
        final ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        //设置插值器
        anim.setInterpolator(new BounceInterpolator());
        //设置监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            //每当Point的值有改变的时候，都会调用onAnimationUpdate()方法
            @Override
            public void onAnimationUpdate(@android.support.annotation.NonNull ValueAnimator animation) {
                //更新curPoint，即更新当前坐标
                curPoint = (Point) animation.getAnimatedValue();
                // 刷新，重现调用onDraw()方法
                // 由于curPoint的值改变，那么绘制的位置也会改变，也就实现了一个从左上到右下的平移动画
                invalidate();
            }
        });
        anim.setDuration(5000);
        anim.start();
    }
}